












package	io	.	reactivex	.	processors	;	

import	java	.	util	.	concurrent	.	atomic	.	*	;	

import	org	.	reactivestreams	.	*	;	

import	io	.	reactivex	.	annotations	.	*	;	
import	io	.	reactivex	.	exceptions	.	*	;	
import	io	.	reactivex	.	internal	.	functions	.	ObjectHelper	;	
import	io	.	reactivex	.	internal	.	fuseable	.	*	;	
import	io	.	reactivex	.	internal	.	queue	.	*	;	
import	io	.	reactivex	.	internal	.	subscriptions	.	*	;	
import	io	.	reactivex	.	plugins	.	RxJavaPlugins	;	






































































































@Experimental	
@BackpressureSupport	(	BackpressureKind	.	FULL	)	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	class	MulticastProcessor	<	T	>	extends	FlowableProcessor	<	T	>	{	

final	AtomicInteger	wip	;	

final	AtomicReference	<	Subscription	>	upstream	;	

final	AtomicReference	<	MulticastSubscription	<	T	>	[	]	>	subscribers	;	

final	AtomicBoolean	once	;	

final	int	bufferSize	;	

final	int	limit	;	

final	boolean	refcount	;	

volatile	SimpleQueue	<	T	>	queue	;	

volatile	boolean	done	;	
volatile	Throwable	error	;	

int	consumed	;	

int	fusionMode	;	

@SuppressWarnings	(	"str"	)	
static	final	MulticastSubscription	[	]	EMPTY	=	new	MulticastSubscription	[	0	]	;	

@SuppressWarnings	(	"str"	)	
static	final	MulticastSubscription	[	]	TERMINATED	=	new	MulticastSubscription	[	0	]	;	







@CheckReturnValue	
@NonNull	
public	static	<	T	>	MulticastProcessor	<	T	>	create	(	)	{	
return	new	MulticastProcessor	<	T	>	(	bufferSize	(	)	,	false	)	;	
}	









@CheckReturnValue	
@NonNull	
public	static	<	T	>	MulticastProcessor	<	T	>	create	(	boolean	refCount	)	{	
return	new	MulticastProcessor	<	T	>	(	bufferSize	(	)	,	refCount	)	;	
}	







@CheckReturnValue	
@NonNull	
public	static	<	T	>	MulticastProcessor	<	T	>	create	(	int	bufferSize	)	{	
return	new	MulticastProcessor	<	T	>	(	bufferSize	,	false	)	;	
}	










@CheckReturnValue	
@NonNull	
public	static	<	T	>	MulticastProcessor	<	T	>	create	(	int	bufferSize	,	boolean	refCount	)	{	
return	new	MulticastProcessor	<	T	>	(	bufferSize	,	refCount	)	;	
}	








@SuppressWarnings	(	"str"	)	
MulticastProcessor	(	int	bufferSize	,	boolean	refCount	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
this	.	bufferSize	=	bufferSize	;	
this	.	limit	=	bufferSize	-	(	bufferSize	>	>	2	)	;	
this	.	wip	=	new	AtomicInteger	(	)	;	
this	.	subscribers	=	new	AtomicReference	<	MulticastSubscription	<	T	>	[	]	>	(	EMPTY	)	;	
this	.	upstream	=	new	AtomicReference	<	Subscription	>	(	)	;	
this	.	refcount	=	refCount	;	
this	.	once	=	new	AtomicBoolean	(	)	;	
}	







public	void	start	(	)	{	
if	(	SubscriptionHelper	.	setOnce	(	upstream	,	EmptySubscription	.	INSTANCE	)	)	{	
queue	=	new	SpscArrayQueue	<	T	>	(	bufferSize	)	;	
}	
}	







public	void	startUnbounded	(	)	{	
if	(	SubscriptionHelper	.	setOnce	(	upstream	,	EmptySubscription	.	INSTANCE	)	)	{	
queue	=	new	SpscLinkedArrayQueue	<	T	>	(	bufferSize	)	;	
}	
}	

@Override	
public	void	onSubscribe	(	Subscription	s	)	{	
if	(	SubscriptionHelper	.	setOnce	(	upstream	,	s	)	)	{	
if	(	s	instanceof	QueueSubscription	)	{	
@SuppressWarnings	(	"str"	)	
QueueSubscription	<	T	>	qs	=	(	QueueSubscription	<	T	>	)	s	;	

int	m	=	qs	.	requestFusion	(	QueueSubscription	.	ANY	)	;	
if	(	m	=	=	QueueSubscription	.	SYNC	)	{	
fusionMode	=	m	;	
queue	=	qs	;	
done	=	true	;	
drain	(	)	;	
return	;	
}	
if	(	m	=	=	QueueSubscription	.	ASYNC	)	{	
fusionMode	=	m	;	
queue	=	qs	;	

s	.	request	(	bufferSize	)	;	
return	;	
}	
}	

queue	=	new	SpscArrayQueue	<	T	>	(	bufferSize	)	;	

s	.	request	(	bufferSize	)	;	
}	
}	

@Override	
public	void	onNext	(	T	t	)	{	
if	(	once	.	get	(	)	)	{	
return	;	
}	
if	(	fusionMode	=	=	QueueSubscription	.	NONE	)	{	
ObjectHelper	.	requireNonNull	(	t	,	"str"	)	;	
if	(	!	queue	.	offer	(	t	)	)	{	
SubscriptionHelper	.	cancel	(	upstream	)	;	
onError	(	new	MissingBackpressureException	(	)	)	;	
return	;	
}	
}	
drain	(	)	;	
}	







public	boolean	offer	(	T	t	)	{	
if	(	once	.	get	(	)	)	{	
return	false	;	
}	
ObjectHelper	.	requireNonNull	(	t	,	"str"	)	;	
if	(	fusionMode	=	=	QueueSubscription	.	NONE	)	{	
if	(	queue	.	offer	(	t	)	)	{	
drain	(	)	;	
return	true	;	
}	
}	
return	false	;	
}	

@Override	
public	void	onError	(	Throwable	t	)	{	
ObjectHelper	.	requireNonNull	(	t	,	"str"	)	;	
if	(	once	.	compareAndSet	(	false	,	true	)	)	{	
error	=	t	;	
done	=	true	;	
drain	(	)	;	
}	else	{	
RxJavaPlugins	.	onError	(	t	)	;	
}	
}	

@Override	
public	void	onComplete	(	)	{	
if	(	once	.	compareAndSet	(	false	,	true	)	)	{	
done	=	true	;	
drain	(	)	;	
}	
}	

@Override	
public	boolean	hasSubscribers	(	)	{	
return	subscribers	.	get	(	)	.	length	!	=	0	;	
}	

@Override	
public	boolean	hasThrowable	(	)	{	
return	once	.	get	(	)	&	&	error	!	=	null	;	
}	

@Override	
public	boolean	hasComplete	(	)	{	
return	once	.	get	(	)	&	&	error	=	=	null	;	
}	

@Override	
public	Throwable	getThrowable	(	)	{	
return	once	.	get	(	)	?	error	:	null	;	
}	

@Override	
protected	void	subscribeActual	(	Subscriber	<	?	super	T	>	s	)	{	
MulticastSubscription	<	T	>	ms	=	new	MulticastSubscription	<	T	>	(	s	,	this	)	;	
s	.	onSubscribe	(	ms	)	;	
if	(	add	(	ms	)	)	{	
if	(	ms	.	get	(	)	=	=	Long	.	MIN_VALUE	)	{	
remove	(	ms	)	;	
}	else	{	
drain	(	)	;	
}	
}	else	{	
if	(	once	.	get	(	)	|	|	!	refcount	)	{	
Throwable	ex	=	error	;	
if	(	ex	!	=	null	)	{	
s	.	onError	(	ex	)	;	
return	;	
}	
}	
s	.	onComplete	(	)	;	
}	
}	

boolean	add	(	MulticastSubscription	<	T	>	inner	)	{	
for	(	;	;	)	{	
MulticastSubscription	<	T	>	[	]	a	=	subscribers	.	get	(	)	;	
if	(	a	=	=	TERMINATED	)	{	
return	false	;	
}	
int	n	=	a	.	length	;	
@SuppressWarnings	(	"str"	)	
MulticastSubscription	<	T	>	[	]	b	=	new	MulticastSubscription	[	n	+	1	]	;	
System	.	arraycopy	(	a	,	0	,	b	,	0	,	n	)	;	
b	[	n	]	=	inner	;	
if	(	subscribers	.	compareAndSet	(	a	,	b	)	)	{	
return	true	;	
}	
}	
}	

@SuppressWarnings	(	"str"	)	
void	remove	(	MulticastSubscription	<	T	>	inner	)	{	
for	(	;	;	)	{	
MulticastSubscription	<	T	>	[	]	a	=	subscribers	.	get	(	)	;	
int	n	=	a	.	length	;	
if	(	n	=	=	0	)	{	
return	;	
}	

int	j	=	-	1	;	
for	(	int	i	=	0	;	i	<	n	;	i	+	+	)	{	
if	(	a	[	i	]	=	=	inner	)	{	
j	=	i	;	
break	;	
}	
}	

if	(	j	<	0	)	{	
break	;	
}	

if	(	n	=	=	1	)	{	
if	(	refcount	)	{	
if	(	subscribers	.	compareAndSet	(	a	,	TERMINATED	)	)	{	
SubscriptionHelper	.	cancel	(	upstream	)	;	
once	.	set	(	true	)	;	
break	;	
}	
}	else	{	
if	(	subscribers	.	compareAndSet	(	a	,	EMPTY	)	)	{	
break	;	
}	
}	
}	else	{	
MulticastSubscription	<	T	>	[	]	b	=	new	MulticastSubscription	[	n	-	1	]	;	
System	.	arraycopy	(	a	,	0	,	b	,	0	,	j	)	;	
System	.	arraycopy	(	a	,	j	+	1	,	b	,	j	,	n	-	j	-	1	)	;	
if	(	subscribers	.	compareAndSet	(	a	,	b	)	)	{	
break	;	
}	
}	
}	
}	

@SuppressWarnings	(	"str"	)	
void	drain	(	)	{	
if	(	wip	.	getAndIncrement	(	)	!	=	0	)	{	
return	;	
}	

int	missed	=	1	;	
AtomicReference	<	MulticastSubscription	<	T	>	[	]	>	subs	=	subscribers	;	
int	c	=	consumed	;	
int	lim	=	limit	;	
int	fm	=	fusionMode	;	
outer:	
for	(	;	;	)	{	

SimpleQueue	<	T	>	q	=	queue	;	

if	(	q	!	=	null	)	{	
MulticastSubscription	<	T	>	[	]	as	=	subs	.	get	(	)	;	
int	n	=	as	.	length	;	

if	(	n	!	=	0	)	{	
long	r	=	-	1L	;	

for	(	MulticastSubscription	<	T	>	a	:	as	)	{	
long	ra	=	a	.	get	(	)	;	
if	(	ra	>	=	0	L	)	{	
if	(	r	=	=	-	1L	)	{	
r	=	ra	-	a	.	emitted	;	
}	else	{	
r	=	Math	.	min	(	r	,	ra	-	a	.	emitted	)	;	
}	
}	
}	

while	(	r	>	0	L	)	{	
MulticastSubscription	<	T	>	[	]	bs	=	subs	.	get	(	)	;	

if	(	bs	=	=	TERMINATED	)	{	
q	.	clear	(	)	;	
return	;	
}	

if	(	as	!	=	bs	)	{	
continue	outer	;	
}	

boolean	d	=	done	;	

T	v	;	

try	{	
v	=	q	.	poll	(	)	;	
}	catch	(	Throwable	ex	)	{	
Exceptions	.	throwIfFatal	(	ex	)	;	
SubscriptionHelper	.	cancel	(	upstream	)	;	
d	=	true	;	
v	=	null	;	
error	=	ex	;	
done	=	true	;	
}	
boolean	empty	=	v	=	=	null	;	

if	(	d	&	&	empty	)	{	
Throwable	ex	=	error	;	
if	(	ex	!	=	null	)	{	
for	(	MulticastSubscription	<	T	>	inner	:	subs	.	getAndSet	(	TERMINATED	)	)	{	
inner	.	onError	(	ex	)	;	
}	
}	else	{	
for	(	MulticastSubscription	<	T	>	inner	:	subs	.	getAndSet	(	TERMINATED	)	)	{	
inner	.	onComplete	(	)	;	
}	
}	
return	;	
}	

if	(	empty	)	{	
break	;	
}	

for	(	MulticastSubscription	<	T	>	inner	:	as	)	{	
inner	.	onNext	(	v	)	;	
}	

r	-	-	;	

if	(	fm	!	=	QueueSubscription	.	SYNC	)	{	
if	(	+	+	c	=	=	lim	)	{	
c	=	0	;	
upstream	.	get	(	)	.	request	(	lim	)	;	
}	
}	
}	

if	(	r	=	=	0	)	{	
MulticastSubscription	<	T	>	[	]	bs	=	subs	.	get	(	)	;	

if	(	bs	=	=	TERMINATED	)	{	
q	.	clear	(	)	;	
return	;	
}	

if	(	as	!	=	bs	)	{	
continue	outer	;	
}	

if	(	done	&	&	q	.	isEmpty	(	)	)	{	
Throwable	ex	=	error	;	
if	(	ex	!	=	null	)	{	
for	(	MulticastSubscription	<	T	>	inner	:	subs	.	getAndSet	(	TERMINATED	)	)	{	
inner	.	onError	(	ex	)	;	
}	
}	else	{	
for	(	MulticastSubscription	<	T	>	inner	:	subs	.	getAndSet	(	TERMINATED	)	)	{	
inner	.	onComplete	(	)	;	
}	
}	
return	;	
}	
}	
}	
}	

missed	=	wip	.	addAndGet	(	-	missed	)	;	
if	(	missed	=	=	0	)	{	
break	;	
}	
}	
}	

static	final	class	MulticastSubscription	<	T	>	extends	AtomicLong	implements	Subscription	{	

private	static	final	long	serialVersionUID	=	-	363282618957264509L	;	

final	Subscriber	<	?	super	T	>	actual	;	

final	MulticastProcessor	<	T	>	parent	;	

long	emitted	;	

MulticastSubscription	(	Subscriber	<	?	super	T	>	actual	,	MulticastProcessor	<	T	>	parent	)	{	
this	.	actual	=	actual	;	
this	.	parent	=	parent	;	
}	

@Override	
public	void	request	(	long	n	)	{	
if	(	SubscriptionHelper	.	validate	(	n	)	)	{	
for	(	;	;	)	{	
long	r	=	get	(	)	;	
if	(	r	=	=	Long	.	MIN_VALUE	|	|	r	=	=	Long	.	MAX_VALUE	)	{	
break	;	
}	
long	u	=	r	+	n	;	
if	(	u	<	0	L	)	{	
u	=	Long	.	MAX_VALUE	;	
}	
if	(	compareAndSet	(	r	,	u	)	)	{	
parent	.	drain	(	)	;	
break	;	
}	
}	
}	
}	

@Override	
public	void	cancel	(	)	{	
if	(	getAndSet	(	Long	.	MIN_VALUE	)	!	=	Long	.	MIN_VALUE	)	{	
parent	.	remove	(	this	)	;	
}	
}	

void	onNext	(	T	t	)	{	
if	(	get	(	)	!	=	Long	.	MIN_VALUE	)	{	
emitted	+	+	;	
actual	.	onNext	(	t	)	;	
}	
}	

void	onError	(	Throwable	t	)	{	
if	(	get	(	)	!	=	Long	.	MIN_VALUE	)	{	
actual	.	onError	(	t	)	;	
}	
}	

void	onComplete	(	)	{	
if	(	get	(	)	!	=	Long	.	MIN_VALUE	)	{	
actual	.	onComplete	(	)	;	
}	
}	
}	
}	
