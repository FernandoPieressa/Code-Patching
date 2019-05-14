












package	io	.	reactivex	;	

import	java	.	util	.	*	;	
import	java	.	util	.	concurrent	.	*	;	

import	org	.	reactivestreams	.	Publisher	;	

import	io	.	reactivex	.	annotations	.	*	;	
import	io	.	reactivex	.	disposables	.	Disposable	;	
import	io	.	reactivex	.	exceptions	.	Exceptions	;	
import	io	.	reactivex	.	functions	.	*	;	
import	io	.	reactivex	.	internal	.	functions	.	*	;	
import	io	.	reactivex	.	internal	.	fuseable	.	ScalarCallable	;	
import	io	.	reactivex	.	internal	.	observers	.	*	;	
import	io	.	reactivex	.	internal	.	operators	.	flowable	.	*	;	
import	io	.	reactivex	.	internal	.	operators	.	observable	.	*	;	
import	io	.	reactivex	.	internal	.	util	.	*	;	
import	io	.	reactivex	.	observables	.	*	;	
import	io	.	reactivex	.	observers	.	*	;	
import	io	.	reactivex	.	plugins	.	RxJavaPlugins	;	
import	io	.	reactivex	.	schedulers	.	*	;	






























































public	abstract	class	Observable	<	T	>	implements	ObservableSource	<	T	>	{	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	amb	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableAmb	<	T	>	(	null	,	sources	)	)	;	
}	



















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	ambArray	(	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
int	len	=	sources	.	length	;	
if	(	len	=	=	0	)	{	
return	empty	(	)	;	
}	
if	(	len	=	=	1	)	{	
return	(	Observable	<	T	>	)	wrap	(	sources	[	0	]	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableAmb	<	T	>	(	sources	,	null	)	)	;	
}	








public	static	int	bufferSize	(	)	{	
return	Flowable	.	bufferSize	(	)	;	
}	






































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatest	(	Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	,	int	bufferSize	,	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	combineLatest	(	sources	,	combiner	,	bufferSize	)	;	
}	




































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatest	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	)	{	
return	combineLatest	(	sources	,	combiner	,	bufferSize	(	)	)	;	
}	






































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatest	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	

int	s	=	bufferSize	<	<	1	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCombineLatest	<	T	,	R	>	(	null	,	sources	,	combiner	,	s	,	false	)	)	;	
}	




































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatest	(	ObservableSource	<	?	extends	T	>	[	]	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	)	{	
return	combineLatest	(	sources	,	combiner	,	bufferSize	(	)	)	;	
}	






































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatest	(	ObservableSource	<	?	extends	T	>	[	]	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
if	(	sources	.	length	=	=	0	)	{	
return	empty	(	)	;	
}	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	

int	s	=	bufferSize	<	<	1	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCombineLatest	<	T	,	R	>	(	sources	,	null	,	combiner	,	s	,	false	)	)	;	
}	





























@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
BiFunction	<	?	super	T1	,	?	super	T2	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	)	;	
}	
































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	
Function3	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	)	;	
}	



































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	ObservableSource	<	?	extends	T4	>	source4	,	
Function4	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	)	;	
}	






































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	ObservableSource	<	?	extends	T4	>	source4	,	
ObservableSource	<	?	extends	T5	>	source5	,	
Function5	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	)	;	
}	









































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	ObservableSource	<	?	extends	T4	>	source4	,	
ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
Function6	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	)	;	
}	












































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	T7	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	ObservableSource	<	?	extends	T4	>	source4	,	
ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
ObservableSource	<	?	extends	T7	>	source7	,	
Function7	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	super	T7	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source7	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	,	source7	)	;	
}	















































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	T7	,	T8	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	ObservableSource	<	?	extends	T4	>	source4	,	
ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
ObservableSource	<	?	extends	T7	>	source7	,	ObservableSource	<	?	extends	T8	>	source8	,	
Function8	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	super	T7	,	?	super	T8	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source8	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	,	source7	,	source8	)	;	
}	


















































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	T7	,	T8	,	T9	,	R	>	Observable	<	R	>	combineLatest	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
ObservableSource	<	?	extends	T3	>	source3	,	ObservableSource	<	?	extends	T4	>	source4	,	
ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
ObservableSource	<	?	extends	T7	>	source7	,	ObservableSource	<	?	extends	T8	>	source8	,	
ObservableSource	<	?	extends	T9	>	source9	,	
Function9	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	super	T7	,	?	super	T8	,	?	super	T9	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source8	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source9	,	"str"	)	;	
return	combineLatest	(	Functions	.	toFunction	(	combiner	)	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	,	source7	,	source8	,	source9	)	;	
}	




































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatestDelayError	(	ObservableSource	<	?	extends	T	>	[	]	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	)	{	
return	combineLatestDelayError	(	sources	,	combiner	,	bufferSize	(	)	)	;	
}	







































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatestDelayError	(	Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	,	
int	bufferSize	,	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	combineLatestDelayError	(	sources	,	combiner	,	bufferSize	)	;	
}	







































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatestDelayError	(	ObservableSource	<	?	extends	T	>	[	]	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	,	int	bufferSize	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
if	(	sources	.	length	=	=	0	)	{	
return	empty	(	)	;	
}	
int	s	=	bufferSize	<	<	1	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCombineLatest	<	T	,	R	>	(	sources	,	null	,	combiner	,	s	,	true	)	)	;	
}	





































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatestDelayError	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	)	{	
return	combineLatestDelayError	(	sources	,	combiner	,	bufferSize	(	)	)	;	
}	







































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	combineLatestDelayError	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	combiner	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	

int	s	=	bufferSize	<	<	1	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCombineLatest	<	T	,	R	>	(	null	,	sources	,	combiner	,	s	,	true	)	)	;	
}	














@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concat	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	fromIterable	(	sources	)	.	concatMapDelayError	(	(	Function	)	Functions	.	identity	(	)	,	bufferSize	(	)	,	false	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concat	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	concat	(	sources	,	bufferSize	(	)	)	;	
}	




















@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concat	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMap	(	sources	,	Functions	.	identity	(	)	,	prefetch	,	ErrorMode	.	IMMEDIATE	)	)	;	
}	




















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concat	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	concatArray	(	source1	,	source2	)	;	
}	






















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concat	(	
ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
ObservableSource	<	?	extends	T	>	source3	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
return	concatArray	(	source1	,	source2	,	source3	)	;	
}	
























@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concat	(	
ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
ObservableSource	<	?	extends	T	>	source3	,	ObservableSource	<	?	extends	T	>	source4	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
return	concatArray	(	source1	,	source2	,	source3	,	source4	)	;	
}	
















@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatArray	(	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
if	(	sources	.	length	=	=	0	)	{	
return	empty	(	)	;	
}	else	
if	(	sources	.	length	=	=	1	)	{	
return	wrap	(	(	ObservableSource	<	T	>	)	sources	[	0	]	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMap	(	fromArray	(	sources	)	,	Functions	.	identity	(	)	,	bufferSize	(	)	,	ErrorMode	.	BOUNDARY	)	)	;	
}	















@SuppressWarnings	(	{	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatArrayDelayError	(	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
if	(	sources	.	length	=	=	0	)	{	
return	empty	(	)	;	
}	else	
if	(	sources	.	length	=	=	1	)	{	
return	(	Observable	<	T	>	)	wrap	(	sources	[	0	]	)	;	
}	
return	concatDelayError	(	fromArray	(	sources	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatArrayEager	(	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	concatArrayEager	(	bufferSize	(	)	,	bufferSize	(	)	,	sources	)	;	
}	



















@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatArrayEager	(	int	maxConcurrency	,	int	prefetch	,	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	fromArray	(	sources	)	.	concatMapEagerDelayError	(	(	Function	)	Functions	.	identity	(	)	,	maxConcurrency	,	prefetch	,	false	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatDelayError	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	concatDelayError	(	fromIterable	(	sources	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatDelayError	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	concatDelayError	(	sources	,	bufferSize	(	)	,	true	)	;	
}	


















@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatDelayError	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	prefetch	,	boolean	tillTheEnd	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMap	(	sources	,	Functions	.	identity	(	)	,	prefetch	,	tillTheEnd	?	ErrorMode	.	END	:	ErrorMode	.	BOUNDARY	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatEager	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	concatEager	(	sources	,	bufferSize	(	)	,	bufferSize	(	)	)	;	
}	





















@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatEager	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	maxConcurrency	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	prefetch	,	"str"	)	;	
return	wrap	(	sources	)	.	concatMapEager	(	(	Function	)	Functions	.	identity	(	)	,	maxConcurrency	,	prefetch	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatEager	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	concatEager	(	sources	,	bufferSize	(	)	,	bufferSize	(	)	)	;	
}	





















@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	concatEager	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	maxConcurrency	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	prefetch	,	"str"	)	;	
return	fromIterable	(	sources	)	.	concatMapEagerDelayError	(	(	Function	)	Functions	.	identity	(	)	,	maxConcurrency	,	prefetch	,	false	)	;	
}	













































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	create	(	ObservableOnSubscribe	<	T	>	source	)	{	
ObjectHelper	.	requireNonNull	(	source	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCreate	<	T	>	(	source	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	defer	(	Callable	<	?	extends	ObservableSource	<	?	extends	T	>	>	supplier	)	{	
ObjectHelper	.	requireNonNull	(	supplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDefer	<	T	>	(	supplier	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@SuppressWarnings	(	"str"	)	
public	static	<	T	>	Observable	<	T	>	empty	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	(	Observable	<	T	>	)	ObservableEmpty	.	INSTANCE	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	error	(	Callable	<	?	extends	Throwable	>	errorSupplier	)	{	
ObjectHelper	.	requireNonNull	(	errorSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableError	<	T	>	(	errorSupplier	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	error	(	final	Throwable	exception	)	{	
ObjectHelper	.	requireNonNull	(	exception	,	"str"	)	;	
return	error	(	Functions	.	justCallable	(	exception	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	fromArray	(	T	.	.	.	items	)	{	
ObjectHelper	.	requireNonNull	(	items	,	"str"	)	;	
if	(	items	.	length	=	=	0	)	{	
return	empty	(	)	;	
}	else	
if	(	items	.	length	=	=	1	)	{	
return	just	(	items	[	0	]	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromArray	<	T	>	(	items	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	fromCallable	(	Callable	<	?	extends	T	>	supplier	)	{	
ObjectHelper	.	requireNonNull	(	supplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromCallable	<	T	>	(	supplier	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	fromFuture	(	Future	<	?	extends	T	>	future	)	{	
ObjectHelper	.	requireNonNull	(	future	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromFuture	<	T	>	(	future	,	0	L	,	null	)	)	;	
}	































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	fromFuture	(	Future	<	?	extends	T	>	future	,	long	timeout	,	TimeUnit	unit	)	{	
ObjectHelper	