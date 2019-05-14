












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
ObjectHelper	.	requireNonNull	(	future	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromFuture	<	T	>	(	future	,	timeout	,	unit	)	)	;	
}	


































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	static	<	T	>	Observable	<	T	>	fromFuture	(	Future	<	?	extends	T	>	future	,	long	timeout	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
Observable	<	T	>	o	=	fromFuture	(	future	,	timeout	,	unit	)	;	
return	o	.	subscribeOn	(	scheduler	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	static	<	T	>	Observable	<	T	>	fromFuture	(	Future	<	?	extends	T	>	future	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
Observable	<	T	>	o	=	fromFuture	(	future	)	;	
return	o	.	subscribeOn	(	scheduler	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	fromIterable	(	Iterable	<	?	extends	T	>	source	)	{	
ObjectHelper	.	requireNonNull	(	source	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromIterable	<	T	>	(	source	)	)	;	
}	





























@BackpressureSupport	(	BackpressureKind	.	UNBOUNDED_IN	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	fromPublisher	(	Publisher	<	?	extends	T	>	publisher	)	{	
ObjectHelper	.	requireNonNull	(	publisher	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromPublisher	<	T	>	(	publisher	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	generate	(	final	Consumer	<	Emitter	<	T	>	>	generator	)	{	
ObjectHelper	.	requireNonNull	(	generator	,	"str"	)	;	
return	generate	(	Functions	.	<	Object	>	nullSupplier	(	)	,	
ObservableInternalHelper	.	simpleGenerator	(	generator	)	,	Functions	.	<	Object	>	emptyConsumer	(	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	S	>	Observable	<	T	>	generate	(	Callable	<	S	>	initialState	,	final	BiConsumer	<	S	,	Emitter	<	T	>	>	generator	)	{	
ObjectHelper	.	requireNonNull	(	generator	,	"str"	)	;	
return	generate	(	initialState	,	ObservableInternalHelper	.	simpleBiGenerator	(	generator	)	,	Functions	.	emptyConsumer	(	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	S	>	Observable	<	T	>	generate	(	
final	Callable	<	S	>	initialState	,	
final	BiConsumer	<	S	,	Emitter	<	T	>	>	generator	,	
Consumer	<	?	super	S	>	disposeState	)	{	
ObjectHelper	.	requireNonNull	(	generator	,	"str"	)	;	
return	generate	(	initialState	,	ObservableInternalHelper	.	simpleBiGenerator	(	generator	)	,	disposeState	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	S	>	Observable	<	T	>	generate	(	Callable	<	S	>	initialState	,	BiFunction	<	S	,	Emitter	<	T	>	,	S	>	generator	)	{	
return	generate	(	initialState	,	generator	,	Functions	.	emptyConsumer	(	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	S	>	Observable	<	T	>	generate	(	Callable	<	S	>	initialState	,	BiFunction	<	S	,	Emitter	<	T	>	,	S	>	generator	,	
Consumer	<	?	super	S	>	disposeState	)	{	
ObjectHelper	.	requireNonNull	(	initialState	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	generator	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	disposeState	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableGenerate	<	T	,	S	>	(	initialState	,	generator	,	disposeState	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	static	Observable	<	Long	>	interval	(	long	initialDelay	,	long	period	,	TimeUnit	unit	)	{	
return	interval	(	initialDelay	,	period	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	static	Observable	<	Long	>	interval	(	long	initialDelay	,	long	period	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableInterval	(	Math	.	max	(	0	L	,	initialDelay	)	,	Math	.	max	(	0	L	,	period	)	,	unit	,	scheduler	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	static	Observable	<	Long	>	interval	(	long	period	,	TimeUnit	unit	)	{	
return	interval	(	period	,	period	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	static	Observable	<	Long	>	interval	(	long	period	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	interval	(	period	,	period	,	unit	,	scheduler	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	static	Observable	<	Long	>	intervalRange	(	long	start	,	long	count	,	long	initialDelay	,	long	period	,	TimeUnit	unit	)	{	
return	intervalRange	(	start	,	count	,	initialDelay	,	period	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	static	Observable	<	Long	>	intervalRange	(	long	start	,	long	count	,	long	initialDelay	,	long	period	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
if	(	count	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	+	count	)	;	
}	

if	(	count	=	=	0	L	)	{	
return	Observable	.	<	Long	>	empty	(	)	.	delay	(	initialDelay	,	unit	,	scheduler	)	;	
}	

long	end	=	start	+	(	count	-	1	)	;	
if	(	start	>	0	&	&	end	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	)	;	
}	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableIntervalRange	(	start	,	end	,	Math	.	max	(	0	L	,	initialDelay	)	,	Math	.	max	(	0	L	,	period	)	,	unit	,	scheduler	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item	)	{	
ObjectHelper	.	requireNonNull	(	item	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableJust	<	T	>	(	item	)	)	;	
}	



















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	

return	fromArray	(	item1	,	item2	)	;	
}	





















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	)	;	
}	























@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	)	;	
}	

























@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	,	T	item5	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item5	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	,	item5	)	;	
}	



























@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	,	T	item5	,	T	item6	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item6	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	,	item5	,	item6	)	;	
}	





























@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	,	T	item5	,	T	item6	,	T	item7	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item7	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	,	item5	,	item6	,	item7	)	;	
}	































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	,	T	item5	,	T	item6	,	T	item7	,	T	item8	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item8	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	,	item5	,	item6	,	item7	,	item8	)	;	
}	

































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	,	T	item5	,	T	item6	,	T	item7	,	T	item8	,	T	item9	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item8	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item9	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	,	item5	,	item6	,	item7	,	item8	,	item9	)	;	
}	



































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	just	(	T	item1	,	T	item2	,	T	item3	,	T	item4	,	T	item5	,	T	item6	,	T	item7	,	T	item8	,	T	item9	,	T	item10	)	{	
ObjectHelper	.	requireNonNull	(	item1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item8	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item9	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	item10	,	"str"	)	;	

return	fromArray	(	item1	,	item2	,	item3	,	item4	,	item5	,	item6	,	item7	,	item8	,	item9	,	item10	)	;	
}	









































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	,	int	bufferSize	)	{	
return	fromIterable	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	false	,	maxConcurrency	,	bufferSize	)	;	
}	









































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeArray	(	int	maxConcurrency	,	int	bufferSize	,	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	fromArray	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	false	,	maxConcurrency	,	bufferSize	)	;	
}	


































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	fromIterable	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	)	;	
}	







































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	)	{	
return	fromIterable	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	maxConcurrency	)	;	
}	



































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@SuppressWarnings	(	{	"str"	,	"str"	}	)	
public	static	<	T	>	Observable	<	T	>	merge	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMap	(	sources	,	Functions	.	identity	(	)	,	false	,	Integer	.	MAX_VALUE	,	bufferSize	(	)	)	)	;	
}	









































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	maxConcurrency	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMap	(	sources	,	Functions	.	identity	(	)	,	false	,	maxConcurrency	,	bufferSize	(	)	)	)	;	
}	



































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	fromArray	(	source1	,	source2	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	false	,	2	)	;	
}	





































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	ObservableSource	<	?	extends	T	>	source3	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
return	fromArray	(	source1	,	source2	,	source3	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	false	,	3	)	;	
}	







































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	merge	(	
ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
ObservableSource	<	?	extends	T	>	source3	,	ObservableSource	<	?	extends	T	>	source4	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
return	fromArray	(	source1	,	source2	,	source3	,	source4	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	false	,	4	)	;	
}	

































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeArray	(	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	fromArray	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	sources	.	length	)	;	
}	


























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	fromIterable	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	)	;	
}	






























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	,	int	bufferSize	)	{	
return	fromIterable	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	maxConcurrency	,	bufferSize	)	;	
}	






























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeArrayDelayError	(	int	maxConcurrency	,	int	bufferSize	,	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	fromArray	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	maxConcurrency	,	bufferSize	)	;	
}	




























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	)	{	
return	fromIterable	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	maxConcurrency	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@SuppressWarnings	(	{	"str"	,	"str"	}	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMap	(	sources	,	Functions	.	identity	(	)	,	true	,	Integer	.	MAX_VALUE	,	bufferSize	(	)	)	)	;	
}	






























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	maxConcurrency	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	maxConcurrency	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMap	(	sources	,	Functions	.	identity	(	)	,	true	,	maxConcurrency	,	bufferSize	(	)	)	)	;	
}	



























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	fromArray	(	source1	,	source2	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	2	)	;	
}	






























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	ObservableSource	<	?	extends	T	>	source3	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
return	fromArray	(	source1	,	source2	,	source3	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	3	)	;	
}	
































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeDelayError	(	
ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
ObservableSource	<	?	extends	T	>	source3	,	ObservableSource	<	?	extends	T	>	source4	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
return	fromArray	(	source1	,	source2	,	source3	,	source4	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	4	)	;	
}	


























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	mergeArrayDelayError	(	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
return	fromArray	(	sources	)	.	flatMap	(	(	Function	)	Functions	.	identity	(	)	,	true	,	sources	.	length	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@SuppressWarnings	(	"str"	)	
public	static	<	T	>	Observable	<	T	>	never	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	(	Observable	<	T	>	)	ObservableNever	.	INSTANCE	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	Observable	<	Integer	>	range	(	final	int	start	,	final	int	count	)	{	
if	(	count	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	+	count	)	;	
}	
if	(	count	=	=	0	)	{	
return	empty	(	)	;	
}	
if	(	count	=	=	1	)	{	
return	just	(	start	)	;	
}	
if	(	(	long	)	start	+	(	count	-	1	)	>	Integer	.	MAX_VALUE	)	{	
throw	new	IllegalArgumentException	(	"str"	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableRange	(	start	,	count	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	Observable	<	Long	>	rangeLong	(	long	start	,	long	count	)	{	
if	(	count	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	+	count	)	;	
}	

if	(	count	=	=	0	)	{	
return	empty	(	)	;	
}	

if	(	count	=	=	1	)	{	
return	just	(	start	)	;	
}	

long	end	=	start	+	(	count	-	1	)	;	
if	(	start	>	0	&	&	end	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	)	;	
}	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableRangeLong	(	start	,	count	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Single	<	Boolean	>	sequenceEqual	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	)	{	
return	sequenceEqual	(	source1	,	source2	,	ObjectHelper	.	equalsPredicate	(	)	,	bufferSize	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Single	<	Boolean	>	sequenceEqual	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
BiPredicate	<	?	super	T	,	?	super	T	>	isEqual	)	{	
return	sequenceEqual	(	source1	,	source2	,	isEqual	,	bufferSize	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Single	<	Boolean	>	sequenceEqual	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
BiPredicate	<	?	super	T	,	?	super	T	>	isEqual	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	isEqual	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSequenceEqualSingle	<	T	>	(	source1	,	source2	,	isEqual	,	bufferSize	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Single	<	Boolean	>	sequenceEqual	(	ObservableSource	<	?	extends	T	>	source1	,	ObservableSource	<	?	extends	T	>	source2	,	
int	bufferSize	)	{	
return	sequenceEqual	(	source1	,	source2	,	ObjectHelper	.	equalsPredicate	(	)	,	bufferSize	)	;	
}	




























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	switchOnNext	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSwitchMap	(	sources	,	Functions	.	identity	(	)	,	bufferSize	,	false	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	switchOnNext	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	switchOnNext	(	sources	,	bufferSize	(	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	switchOnNextDelayError	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	)	{	
return	switchOnNextDelayError	(	sources	,	bufferSize	(	)	)	;	
}	






























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	switchOnNextDelayError	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSwitchMap	(	sources	,	Functions	.	identity	(	)	,	prefetch	,	true	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	static	Observable	<	Long	>	timer	(	long	delay	,	TimeUnit	unit	)	{	
return	timer	(	delay	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	static	Observable	<	Long	>	timer	(	long	delay	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableTimer	(	Math	.	max	(	delay	,	0	L	)	,	unit	,	scheduler	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	unsafeCreate	(	ObservableSource	<	T	>	onSubscribe	)	{	
ObjectHelper	.	requireNonNull	(	onSubscribe	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onSubscribe	,	"str"	)	;	
if	(	onSubscribe	instanceof	Observable	)	{	
throw	new	IllegalArgumentException	(	"str"	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromUnsafeSource	<	T	>	(	onSubscribe	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	D	>	Observable	<	T	>	using	(	Callable	<	?	extends	D	>	resourceSupplier	,	Function	<	?	super	D	,	?	extends	ObservableSource	<	?	extends	T	>	>	sourceSupplier	,	Consumer	<	?	super	D	>	disposer	)	{	
return	using	(	resourceSupplier	,	sourceSupplier	,	disposer	,	true	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	D	>	Observable	<	T	>	using	(	Callable	<	?	extends	D	>	resourceSupplier	,	Function	<	?	super	D	,	?	extends	ObservableSource	<	?	extends	T	>	>	sourceSupplier	,	Consumer	<	?	super	D	>	disposer	,	boolean	eager	)	{	
ObjectHelper	.	requireNonNull	(	resourceSupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	sourceSupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	disposer	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableUsing	<	T	,	D	>	(	resourceSupplier	,	sourceSupplier	,	disposer	,	eager	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	>	Observable	<	T	>	wrap	(	ObservableSource	<	T	>	source	)	{	
ObjectHelper	.	requireNonNull	(	source	,	"str"	)	;	
if	(	source	instanceof	Observable	)	{	
return	RxJavaPlugins	.	onAssembly	(	(	Observable	<	T	>	)	source	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFromUnsafeSource	<	T	>	(	source	)	)	;	
}	














































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	zip	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	Function	<	?	super	Object	[	]	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	zipper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableZip	<	T	,	R	>	(	null	,	sources	,	zipper	,	bufferSize	(	)	,	false	)	)	;	
}	














































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	zip	(	ObservableSource	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	final	Function	<	?	super	Object	[	]	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	zipper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableToList	(	sources	,	16	)	
.	flatMap	(	ObservableInternalHelper	.	zipIterable	(	zipper	)	)	)	;	
}	













































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
BiFunction	<	?	super	T1	,	?	super	T2	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	)	;	
}	














































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
BiFunction	<	?	super	T1	,	?	super	T2	,	?	extends	R	>	zipper	,	boolean	delayError	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	delayError	,	bufferSize	(	)	,	source1	,	source2	)	;	
}	















































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	
BiFunction	<	?	super	T1	,	?	super	T2	,	?	extends	R	>	zipper	,	boolean	delayError	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	delayError	,	bufferSize	,	source1	,	source2	)	;	
}	

















































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
Function3	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	)	;	
}	




















































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
ObservableSource	<	?	extends	T4	>	source4	,	
Function4	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	)	;	
}	























































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
ObservableSource	<	?	extends	T4	>	source4	,	ObservableSource	<	?	extends	T5	>	source5	,	
Function5	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	)	;	
}	

























































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
ObservableSource	<	?	extends	T4	>	source4	,	ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
Function6	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	)	;	
}	




























































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	T7	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
ObservableSource	<	?	extends	T4	>	source4	,	ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
ObservableSource	<	?	extends	T7	>	source7	,	
Function7	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	super	T7	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source7	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	,	source7	)	;	
}	































































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	T7	,	T8	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
ObservableSource	<	?	extends	T4	>	source4	,	ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
ObservableSource	<	?	extends	T7	>	source7	,	ObservableSource	<	?	extends	T8	>	source8	,	
Function8	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	super	T7	,	?	super	T8	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source8	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	,	source7	,	source8	)	;	
}	


































































@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T1	,	T2	,	T3	,	T4	,	T5	,	T6	,	T7	,	T8	,	T9	,	R	>	Observable	<	R	>	zip	(	
ObservableSource	<	?	extends	T1	>	source1	,	ObservableSource	<	?	extends	T2	>	source2	,	ObservableSource	<	?	extends	T3	>	source3	,	
ObservableSource	<	?	extends	T4	>	source4	,	ObservableSource	<	?	extends	T5	>	source5	,	ObservableSource	<	?	extends	T6	>	source6	,	
ObservableSource	<	?	extends	T7	>	source7	,	ObservableSource	<	?	extends	T8	>	source8	,	ObservableSource	<	?	extends	T9	>	source9	,	
Function9	<	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	?	super	T5	,	?	super	T6	,	?	super	T7	,	?	super	T8	,	?	super	T9	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	source1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source5	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source6	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source7	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source8	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	source9	,	"str"	)	;	
return	zipArray	(	Functions	.	toFunction	(	zipper	)	,	false	,	bufferSize	(	)	,	source1	,	source2	,	source3	,	source4	,	source5	,	source6	,	source7	,	source8	,	source9	)	;	
}	



















































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	zipArray	(	Function	<	?	super	Object	[	]	,	?	extends	R	>	zipper	,	
boolean	delayError	,	int	bufferSize	,	ObservableSource	<	?	extends	T	>	.	.	.	sources	)	{	
if	(	sources	.	length	=	=	0	)	{	
return	empty	(	)	;	
}	
ObjectHelper	.	requireNonNull	(	zipper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableZip	<	T	,	R	>	(	sources	,	null	,	zipper	,	bufferSize	,	delayError	)	)	;	
}	



















































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	static	<	T	,	R	>	Observable	<	R	>	zipIterable	(	Iterable	<	?	extends	ObservableSource	<	?	extends	T	>	>	sources	,	
Function	<	?	super	Object	[	]	,	?	extends	R	>	zipper	,	boolean	delayError	,	
int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	zipper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	sources	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableZip	<	T	,	R	>	(	null	,	sources	,	zipper	,	bufferSize	,	delayError	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	Boolean	>	all	(	Predicate	<	?	super	T	>	predicate	)	{	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableAllSingle	<	T	>	(	this	,	predicate	)	)	;	
}	


















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	ambWith	(	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	ambArray	(	this	,	other	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	Boolean	>	any	(	Predicate	<	?	super	T	>	predicate	)	{	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableAnySingle	<	T	>	(	this	,	predicate	)	)	;	
}	
















@Experimental	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	R	as	(	@NonNull	ObservableConverter	<	T	,	?	extends	R	>	converter	)	{	
return	ObjectHelper	.	requireNonNull	(	converter	,	"str"	)	.	apply	(	this	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	T	blockingFirst	(	)	{	
BlockingFirstObserver	<	T	>	s	=	new	BlockingFirstObserver	<	T	>	(	)	;	
subscribe	(	s	)	;	
T	v	=	s	.	blockingGet	(	)	;	
if	(	v	!	=	null	)	{	
return	v	;	
}	
throw	new	NoSuchElementException	(	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	T	blockingFirst	(	T	defaultItem	)	{	
BlockingFirstObserver	<	T	>	s	=	new	BlockingFirstObserver	<	T	>	(	)	;	
subscribe	(	s	)	;	
T	v	=	s	.	blockingGet	(	)	;	
return	v	!	=	null	?	v	:	defaultItem	;	
}	




























@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	blockingForEach	(	Consumer	<	?	super	T	>	onNext	)	{	
Iterator	<	T	>	it	=	blockingIterable	(	)	.	iterator	(	)	;	
while	(	it	.	hasNext	(	)	)	{	
try	{	
onNext	.	accept	(	it	.	next	(	)	)	;	
}	catch	(	Throwable	e	)	{	
Exceptions	.	throwIfFatal	(	e	)	;	
(	(	Disposable	)	it	)	.	dispose	(	)	;	
throw	ExceptionHelper	.	wrapOrThrow	(	e	)	;	
}	
}	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Iterable	<	T	>	blockingIterable	(	)	{	
return	blockingIterable	(	bufferSize	(	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Iterable	<	T	>	blockingIterable	(	int	bufferSize	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	new	BlockingObservableIterable	<	T	>	(	this	,	bufferSize	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	T	blockingLast	(	)	{	
BlockingLastObserver	<	T	>	s	=	new	BlockingLastObserver	<	T	>	(	)	;	
subscribe	(	s	)	;	
T	v	=	s	.	blockingGet	(	)	;	
if	(	v	!	=	null	)	{	
return	v	;	
}	
throw	new	NoSuchElementException	(	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	T	blockingLast	(	T	defaultItem	)	{	
BlockingLastObserver	<	T	>	s	=	new	BlockingLastObserver	<	T	>	(	)	;	
subscribe	(	s	)	;	
T	v	=	s	.	blockingGet	(	)	;	
return	v	!	=	null	?	v	:	defaultItem	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Iterable	<	T	>	blockingLatest	(	)	{	
return	new	BlockingObservableLatest	<	T	>	(	this	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Iterable	<	T	>	blockingMostRecent	(	T	initialValue	)	{	
return	new	BlockingObservableMostRecent	<	T	>	(	this	,	initialValue	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Iterable	<	T	>	blockingNext	(	)	{	
return	new	BlockingObservableNext	<	T	>	(	this	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	T	blockingSingle	(	)	{	
T	v	=	singleElement	(	)	.	blockingGet	(	)	;	
if	(	v	=	=	null	)	{	
throw	new	NoSuchElementException	(	)	;	
}	
return	v	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	T	blockingSingle	(	T	defaultItem	)	{	
return	single	(	defaultItem	)	.	blockingGet	(	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Future	<	T	>	toFuture	(	)	{	
return	subscribeWith	(	new	FutureObserver	<	T	>	(	)	)	;	
}	











@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	blockingSubscribe	(	)	{	
ObservableBlockingSubscribe	.	subscribe	(	this	)	;	
}	
















@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	blockingSubscribe	(	Consumer	<	?	super	T	>	onNext	)	{	
ObservableBlockingSubscribe	.	subscribe	(	this	,	onNext	,	Functions	.	ON_ERROR_MISSING	,	Functions	.	EMPTY_ACTION	)	;	
}	













@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	blockingSubscribe	(	Consumer	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	)	{	
ObservableBlockingSubscribe	.	subscribe	(	this	,	onNext	,	onError	,	Functions	.	EMPTY_ACTION	)	;	
}	















@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	blockingSubscribe	(	Consumer	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	,	Action	onComplete	)	{	
ObservableBlockingSubscribe	.	subscribe	(	this	,	onNext	,	onError	,	onComplete	)	;	
}	











@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	blockingSubscribe	(	Observer	<	?	super	T	>	subscriber	)	{	
ObservableBlockingSubscribe	.	subscribe	(	this	,	subscriber	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	int	count	)	{	
return	buffer	(	count	,	count	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	int	count	,	int	skip	)	{	
return	buffer	(	count	,	skip	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	int	count	,	int	skip	,	Callable	<	U	>	bufferSupplier	)	{	
ObjectHelper	.	verifyPositive	(	count	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	skip	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	bufferSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableBuffer	<	T	,	U	>	(	this	,	count	,	skip	,	bufferSupplier	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	int	count	,	Callable	<	U	>	bufferSupplier	)	{	
return	buffer	(	count	,	count	,	bufferSupplier	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	long	timespan	,	long	timeskip	,	TimeUnit	unit	)	{	
return	buffer	(	timespan	,	timeskip	,	unit	,	Schedulers	.	computation	(	)	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	long	timespan	,	long	timeskip	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	buffer	(	timespan	,	timeskip	,	unit	,	scheduler	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	)	;	
}	






























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	<	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	long	timespan	,	long	timeskip	,	TimeUnit	unit	,	Scheduler	scheduler	,	Callable	<	U	>	bufferSupplier	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	bufferSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableBufferTimed	<	T	,	U	>	(	this	,	timespan	,	timeskip	,	unit	,	scheduler	,	bufferSupplier	,	Integer	.	MAX_VALUE	,	false	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	long	timespan	,	TimeUnit	unit	)	{	
return	buffer	(	timespan	,	unit	,	Schedulers	.	computation	(	)	,	Integer	.	MAX_VALUE	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	long	timespan	,	TimeUnit	unit	,	int	count	)	{	
return	buffer	(	timespan	,	unit	,	Schedulers	.	computation	(	)	,	count	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	long	timespan	,	TimeUnit	unit	,	Scheduler	scheduler	,	int	count	)	{	
return	buffer	(	timespan	,	unit	,	scheduler	,	count	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	,	false	)	;	
}	



































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	<	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	
long	timespan	,	TimeUnit	unit	,	
Scheduler	scheduler	,	int	count	,	
Callable	<	U	>	bufferSupplier	,	
boolean	restartTimerOnMaxSize	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	bufferSupplier	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	count	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableBufferTimed	<	T	,	U	>	(	this	,	timespan	,	timespan	,	unit	,	scheduler	,	bufferSupplier	,	count	,	restartTimerOnMaxSize	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	List	<	T	>	>	buffer	(	long	timespan	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	buffer	(	timespan	,	unit	,	scheduler	,	Integer	.	MAX_VALUE	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	,	false	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	TOpening	,	TClosing	>	Observable	<	List	<	T	>	>	buffer	(	
ObservableSource	<	?	extends	TOpening	>	openingIndicator	,	
Function	<	?	super	TOpening	,	?	extends	ObservableSource	<	?	extends	TClosing	>	>	closingIndicator	)	{	
return	buffer	(	openingIndicator	,	closingIndicator	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	TOpening	,	TClosing	,	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	
ObservableSource	<	?	extends	TOpening	>	openingIndicator	,	
Function	<	?	super	TOpening	,	?	extends	ObservableSource	<	?	extends	TClosing	>	>	closingIndicator	,	
Callable	<	U	>	bufferSupplier	)	{	
ObjectHelper	.	requireNonNull	(	openingIndicator	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	closingIndicator	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	bufferSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableBufferBoundary	<	T	,	U	,	TOpening	,	TClosing	>	(	this	,	openingIndicator	,	closingIndicator	,	bufferSupplier	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	List	<	T	>	>	buffer	(	ObservableSource	<	B	>	boundary	)	{	
return	buffer	(	boundary	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	List	<	T	>	>	buffer	(	ObservableSource	<	B	>	boundary	,	final	int	initialCapacity	)	{	
ObjectHelper	.	verifyPositive	(	initialCapacity	,	"str"	)	;	
return	buffer	(	boundary	,	Functions	.	<	T	>	createArrayList	(	initialCapacity	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	,	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	ObservableSource	<	B	>	boundary	,	Callable	<	U	>	bufferSupplier	)	{	
ObjectHelper	.	requireNonNull	(	boundary	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	bufferSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableBufferExactBoundary	<	T	,	U	,	B	>	(	this	,	boundary	,	bufferSupplier	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	List	<	T	>	>	buffer	(	Callable	<	?	extends	ObservableSource	<	B	>	>	boundarySupplier	)	{	
return	buffer	(	boundarySupplier	,	ArrayListSupplier	.	<	T	>	asCallable	(	)	)	;	

}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	,	U	extends	Collection	<	?	super	T	>	>	Observable	<	U	>	buffer	(	Callable	<	?	extends	ObservableSource	<	B	>	>	boundarySupplier	,	Callable	<	U	>	bufferSupplier	)	{	
ObjectHelper	.	requireNonNull	(	boundarySupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	bufferSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableBufferBoundarySupplier	<	T	,	U	,	B	>	(	this	,	boundarySupplier	,	bufferSupplier	)	)	;	
}	

















































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	cache	(	)	{	
return	ObservableCache	.	from	(	this	)	;	
}	





















































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	cacheWithInitialCapacity	(	int	initialCapacity	)	{	
return	ObservableCache	.	from	(	this	,	initialCapacity	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	U	>	cast	(	final	Class	<	U	>	clazz	)	{	
ObjectHelper	.	requireNonNull	(	clazz	,	"str"	)	;	
return	map	(	Functions	.	castFunction	(	clazz	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Single	<	U	>	collect	(	Callable	<	?	extends	U	>	initialValueSupplier	,	BiConsumer	<	?	super	U	,	?	super	T	>	collector	)	{	
ObjectHelper	.	requireNonNull	(	initialValueSupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	collector	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCollectSingle	<	T	,	U	>	(	this	,	initialValueSupplier	,	collector	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Single	<	U	>	collectInto	(	final	U	initialValue	,	BiConsumer	<	?	super	U	,	?	super	T	>	collector	)	{	
ObjectHelper	.	requireNonNull	(	initialValue	,	"str"	)	;	
return	collect	(	Functions	.	justCallable	(	initialValue	)	,	collector	)	;	
}	




















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	compose	(	ObservableTransformer	<	?	super	T	,	?	extends	R	>	composer	)	{	
return	wrap	(	(	(	ObservableTransformer	<	T	,	R	>	)	ObjectHelper	.	requireNonNull	(	composer	,	"str"	)	)	.	apply	(	this	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	)	{	
return	concatMap	(	mapper	,	2	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
if	(	this	instanceof	ScalarCallable	)	{	
@SuppressWarnings	(	"str"	)	
T	v	=	(	(	ScalarCallable	<	T	>	)	this	)	.	call	(	)	;	
if	(	v	=	=	null	)	{	
return	empty	(	)	;	
}	
return	ObservableScalarXMap	.	scalarXMap	(	v	,	mapper	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMap	<	T	,	R	>	(	this	,	mapper	,	prefetch	,	ErrorMode	.	IMMEDIATE	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMapDelayError	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	)	{	
return	concatMapDelayError	(	mapper	,	bufferSize	(	)	,	true	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMapDelayError	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	
int	prefetch	,	boolean	tillTheEnd	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
if	(	this	instanceof	ScalarCallable	)	{	
@SuppressWarnings	(	"str"	)	
T	v	=	(	(	ScalarCallable	<	T	>	)	this	)	.	call	(	)	;	
if	(	v	=	=	null	)	{	
return	empty	(	)	;	
}	
return	ObservableScalarXMap	.	scalarXMap	(	v	,	mapper	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMap	<	T	,	R	>	(	this	,	mapper	,	prefetch	,	tillTheEnd	?	ErrorMode	.	END	:	ErrorMode	.	BOUNDARY	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMapEager	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	)	{	
return	concatMapEager	(	mapper	,	Integer	.	MAX_VALUE	,	bufferSize	(	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMapEager	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	
int	maxConcurrency	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	maxConcurrency	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMapEager	<	T	,	R	>	(	this	,	mapper	,	ErrorMode	.	IMMEDIATE	,	maxConcurrency	,	prefetch	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMapEagerDelayError	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	
boolean	tillTheEnd	)	{	
return	concatMapEagerDelayError	(	mapper	,	Integer	.	MAX_VALUE	,	bufferSize	(	)	,	tillTheEnd	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	concatMapEagerDelayError	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	
int	maxConcurrency	,	int	prefetch	,	boolean	tillTheEnd	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	maxConcurrency	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMapEager	<	T	,	R	>	(	this	,	mapper	,	tillTheEnd	?	ErrorMode	.	END	:	ErrorMode	.	BOUNDARY	,	maxConcurrency	,	prefetch	)	)	;	
}	
















@Experimental	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Completable	concatMapCompletable	(	Function	<	?	super	T	,	?	extends	CompletableSource	>	mapper	)	{	
return	concatMapCompletable	(	mapper	,	2	)	;	
}	




















@Experimental	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Completable	concatMapCompletable	(	Function	<	?	super	T	,	?	extends	CompletableSource	>	mapper	,	int	capacityHint	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	capacityHint	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableConcatMapCompletable	<	T	>	(	this	,	mapper	,	capacityHint	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	U	>	concatMapIterable	(	final	Function	<	?	super	T	,	?	extends	Iterable	<	?	extends	U	>	>	mapper	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlattenIterable	<	T	,	U	>	(	this	,	mapper	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	U	>	concatMapIterable	(	final	Function	<	?	super	T	,	?	extends	Iterable	<	?	extends	U	>	>	mapper	,	int	prefetch	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	prefetch	,	"str"	)	;	
return	concatMap	(	ObservableInternalHelper	.	flatMapIntoIterable	(	mapper	)	,	prefetch	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	concatWith	(	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	concat	(	this	,	other	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	Boolean	>	contains	(	final	Object	element	)	{	
ObjectHelper	.	requireNonNull	(	element	,	"str"	)	;	
return	any	(	Functions	.	equalsWith	(	element	)	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	Long	>	count	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableCountSingle	<	T	>	(	this	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	debounce	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	U	>	>	debounceSelector	)	{	
ObjectHelper	.	requireNonNull	(	debounceSelector	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDebounce	<	T	,	U	>	(	this	,	debounceSelector	)	)	;	
}	
































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	debounce	(	long	timeout	,	TimeUnit	unit	)	{	
return	debounce	(	timeout	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	



































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	debounce	(	long	timeout	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDebounceTimed	<	T	>	(	this	,	timeout	,	unit	,	scheduler	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	defaultIfEmpty	(	T	defaultItem	)	{	
ObjectHelper	.	requireNonNull	(	defaultItem	,	"str"	)	;	
return	switchIfEmpty	(	just	(	defaultItem	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	delay	(	final	Function	<	?	super	T	,	?	extends	ObservableSource	<	U	>	>	itemDelay	)	{	
ObjectHelper	.	requireNonNull	(	itemDelay	,	"str"	)	;	
return	flatMap	(	ObservableInternalHelper	.	itemDelay	(	itemDelay	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	delay	(	long	delay	,	TimeUnit	unit	)	{	
return	delay	(	delay	,	unit	,	Schedulers	.	computation	(	)	,	false	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	delay	(	long	delay	,	TimeUnit	unit	,	boolean	delayError	)	{	
return	delay	(	delay	,	unit	,	Schedulers	.	computation	(	)	,	delayError	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	delay	(	long	delay	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	delay	(	delay	,	unit	,	scheduler	,	false	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	delay	(	long	delay	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	delayError	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableDelay	<	T	>	(	this	,	delay	,	unit	,	scheduler	,	delayError	)	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	V	>	Observable	<	T	>	delay	(	ObservableSource	<	U	>	subscriptionDelay	,	
Function	<	?	super	T	,	?	extends	ObservableSource	<	V	>	>	itemDelay	)	{	
return	delaySubscription	(	subscriptionDelay	)	.	delay	(	itemDelay	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	delaySubscription	(	ObservableSource	<	U	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDelaySubscriptionOther	<	T	,	U	>	(	this	,	other	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	delaySubscription	(	long	delay	,	TimeUnit	unit	)	{	
return	delaySubscription	(	delay	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	delaySubscription	(	long	delay	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	delaySubscription	(	timer	(	delay	,	unit	,	scheduler	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	T2	>	Observable	<	T2	>	dematerialize	(	)	{	
@SuppressWarnings	(	"str"	)	
Observable	<	Notification	<	T2	>	>	m	=	(	Observable	<	Notification	<	T2	>	>	)	this	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDematerialize	<	T2	>	(	m	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	distinct	(	)	{	
return	distinct	(	Functions	.	identity	(	)	,	Functions	.	createHashSet	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Observable	<	T	>	distinct	(	Function	<	?	super	T	,	K	>	keySelector	)	{	
return	distinct	(	keySelector	,	Functions	.	createHashSet	(	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Observable	<	T	>	distinct	(	Function	<	?	super	T	,	K	>	keySelector	,	Callable	<	?	extends	Collection	<	?	super	K	>	>	collectionSupplier	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	collectionSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDistinct	<	T	,	K	>	(	this	,	keySelector	,	collectionSupplier	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	distinctUntilChanged	(	)	{	
return	distinctUntilChanged	(	Functions	.	identity	(	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Observable	<	T	>	distinctUntilChanged	(	Function	<	?	super	T	,	K	>	keySelector	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDistinctUntilChanged	<	T	,	K	>	(	this	,	keySelector	,	ObjectHelper	.	equalsPredicate	(	)	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	distinctUntilChanged	(	BiPredicate	<	?	super	T	,	?	super	T	>	comparer	)	{	
ObjectHelper	.	requireNonNull	(	comparer	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDistinctUntilChanged	<	T	,	T	>	(	this	,	Functions	.	<	T	>	identity	(	)	,	comparer	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doAfterNext	(	Consumer	<	?	super	T	>	onAfterNext	)	{	
ObjectHelper	.	requireNonNull	(	onAfterNext	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDoAfterNext	<	T	>	(	this	,	onAfterNext	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doAfterTerminate	(	Action	onFinally	)	{	
ObjectHelper	.	requireNonNull	(	onFinally	,	"str"	)	;	
return	doOnEach	(	Functions	.	emptyConsumer	(	)	,	Functions	.	emptyConsumer	(	)	,	Functions	.	EMPTY_ACTION	,	onFinally	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doFinally	(	Action	onFinally	)	{	
ObjectHelper	.	requireNonNull	(	onFinally	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDoFinally	<	T	>	(	this	,	onFinally	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnDispose	(	Action	onDispose	)	{	
return	doOnLifecycle	(	Functions	.	emptyConsumer	(	)	,	onDispose	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnComplete	(	Action	onComplete	)	{	
return	doOnEach	(	Functions	.	emptyConsumer	(	)	,	Functions	.	emptyConsumer	(	)	,	onComplete	,	Functions	.	EMPTY_ACTION	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
private	Observable	<	T	>	doOnEach	(	Consumer	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	,	Action	onComplete	,	Action	onAfterTerminate	)	{	
ObjectHelper	.	requireNonNull	(	onNext	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onError	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onComplete	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onAfterTerminate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDoOnEach	<	T	>	(	this	,	onNext	,	onError	,	onComplete	,	onAfterTerminate	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnEach	(	final	Consumer	<	?	super	Notification	<	T	>	>	onNotification	)	{	
ObjectHelper	.	requireNonNull	(	onNotification	,	"str"	)	;	
return	doOnEach	(	
Functions	.	notificationOnNext	(	onNotification	)	,	
Functions	.	notificationOnError	(	onNotification	)	,	
Functions	.	notificationOnComplete	(	onNotification	)	,	
Functions	.	EMPTY_ACTION	
)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnEach	(	final	Observer	<	?	super	T	>	observer	)	{	
ObjectHelper	.	requireNonNull	(	observer	,	"str"	)	;	
return	doOnEach	(	
ObservableInternalHelper	.	observerOnNext	(	observer	)	,	
ObservableInternalHelper	.	observerOnError	(	observer	)	,	
ObservableInternalHelper	.	observerOnComplete	(	observer	)	,	
Functions	.	EMPTY_ACTION	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnError	(	Consumer	<	?	super	Throwable	>	onError	)	{	
return	doOnEach	(	Functions	.	emptyConsumer	(	)	,	onError	,	Functions	.	EMPTY_ACTION	,	Functions	.	EMPTY_ACTION	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnLifecycle	(	final	Consumer	<	?	super	Disposable	>	onSubscribe	,	final	Action	onDispose	)	{	
ObjectHelper	.	requireNonNull	(	onSubscribe	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onDispose	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDoOnLifecycle	<	T	>	(	this	,	onSubscribe	,	onDispose	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnNext	(	Consumer	<	?	super	T	>	onNext	)	{	
return	doOnEach	(	onNext	,	Functions	.	emptyConsumer	(	)	,	Functions	.	EMPTY_ACTION	,	Functions	.	EMPTY_ACTION	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnSubscribe	(	Consumer	<	?	super	Disposable	>	onSubscribe	)	{	
return	doOnLifecycle	(	onSubscribe	,	Functions	.	EMPTY_ACTION	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	doOnTerminate	(	final	Action	onTerminate	)	{	
ObjectHelper	.	requireNonNull	(	onTerminate	,	"str"	)	;	
return	doOnEach	(	Functions	.	emptyConsumer	(	)	,	
Functions	.	actionConsumer	(	onTerminate	)	,	onTerminate	,	
Functions	.	EMPTY_ACTION	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Maybe	<	T	>	elementAt	(	long	index	)	{	
if	(	index	<	0	)	{	
throw	new	IndexOutOfBoundsException	(	"str"	+	index	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableElementAtMaybe	<	T	>	(	this	,	index	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	elementAt	(	long	index	,	T	defaultItem	)	{	
if	(	index	<	0	)	{	
throw	new	IndexOutOfBoundsException	(	"str"	+	index	)	;	
}	
ObjectHelper	.	requireNonNull	(	defaultItem	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableElementAtSingle	<	T	>	(	this	,	index	,	defaultItem	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	elementAtOrError	(	long	index	)	{	
if	(	index	<	0	)	{	
throw	new	IndexOutOfBoundsException	(	"str"	+	index	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableElementAtSingle	<	T	>	(	this	,	index	,	null	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	filter	(	Predicate	<	?	super	T	>	predicate	)	{	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFilter	<	T	>	(	this	,	predicate	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Maybe	<	T	>	firstElement	(	)	{	
return	elementAt	(	0	L	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	first	(	T	defaultItem	)	{	
return	elementAt	(	0	L	,	defaultItem	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	firstOrError	(	)	{	
return	elementAtOrError	(	0	L	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	)	{	
return	flatMap	(	mapper	,	false	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	boolean	delayErrors	)	{	
return	flatMap	(	mapper	,	delayErrors	,	Integer	.	MAX_VALUE	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	boolean	delayErrors	,	int	maxConcurrency	)	{	
return	flatMap	(	mapper	,	delayErrors	,	maxConcurrency	,	bufferSize	(	)	)	;	
}	






























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	
boolean	delayErrors	,	int	maxConcurrency	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	maxConcurrency	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
if	(	this	instanceof	ScalarCallable	)	{	
@SuppressWarnings	(	"str"	)	
T	v	=	(	(	ScalarCallable	<	T	>	)	this	)	.	call	(	)	;	
if	(	v	=	=	null	)	{	
return	empty	(	)	;	
}	
return	ObservableScalarXMap	.	scalarXMap	(	v	,	mapper	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMap	<	T	,	R	>	(	this	,	mapper	,	delayErrors	,	maxConcurrency	,	bufferSize	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	
Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	onNextMapper	,	
Function	<	?	super	Throwable	,	?	extends	ObservableSource	<	?	extends	R	>	>	onErrorMapper	,	
Callable	<	?	extends	ObservableSource	<	?	extends	R	>	>	onCompleteSupplier	)	{	
ObjectHelper	.	requireNonNull	(	onNextMapper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onErrorMapper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onCompleteSupplier	,	"str"	)	;	
return	merge	(	new	ObservableMapNotification	<	T	,	R	>	(	this	,	onNextMapper	,	onErrorMapper	,	onCompleteSupplier	)	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	
Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	onNextMapper	,	
Function	<	Throwable	,	?	extends	ObservableSource	<	?	extends	R	>	>	onErrorMapper	,	
Callable	<	?	extends	ObservableSource	<	?	extends	R	>	>	onCompleteSupplier	,	
int	maxConcurrency	)	{	
ObjectHelper	.	requireNonNull	(	onNextMapper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onErrorMapper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onCompleteSupplier	,	"str"	)	;	
return	merge	(	new	ObservableMapNotification	<	T	,	R	>	(	this	,	onNextMapper	,	onErrorMapper	,	onCompleteSupplier	)	,	maxConcurrency	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	int	maxConcurrency	)	{	
return	flatMap	(	mapper	,	false	,	maxConcurrency	,	bufferSize	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	U	>	>	mapper	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	resultSelector	)	{	
return	flatMap	(	mapper	,	resultSelector	,	false	,	bufferSize	(	)	,	bufferSize	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	U	>	>	mapper	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	combiner	,	boolean	delayErrors	)	{	
return	flatMap	(	mapper	,	combiner	,	delayErrors	,	bufferSize	(	)	,	bufferSize	(	)	)	;	
}	































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	U	>	>	mapper	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	combiner	,	boolean	delayErrors	,	int	maxConcurrency	)	{	
return	flatMap	(	mapper	,	combiner	,	delayErrors	,	maxConcurrency	,	bufferSize	(	)	)	;	
}	

































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	flatMap	(	final	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	U	>	>	mapper	,	
final	BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	combiner	,	boolean	delayErrors	,	int	maxConcurrency	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
return	flatMap	(	ObservableInternalHelper	.	flatMapWithCombiner	(	mapper	,	combiner	)	,	delayErrors	,	maxConcurrency	,	bufferSize	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	flatMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	U	>	>	mapper	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	combiner	,	int	maxConcurrency	)	{	
return	flatMap	(	mapper	,	combiner	,	false	,	maxConcurrency	,	bufferSize	(	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Completable	flatMapCompletable	(	Function	<	?	super	T	,	?	extends	CompletableSource	>	mapper	)	{	
return	flatMapCompletable	(	mapper	,	false	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Completable	flatMapCompletable	(	Function	<	?	super	T	,	?	extends	CompletableSource	>	mapper	,	boolean	delayErrors	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMapCompletableCompletable	<	T	>	(	this	,	mapper	,	delayErrors	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	U	>	flatMapIterable	(	final	Function	<	?	super	T	,	?	extends	Iterable	<	?	extends	U	>	>	mapper	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlattenIterable	<	T	,	U	>	(	this	,	mapper	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	V	>	Observable	<	V	>	flatMapIterable	(	final	Function	<	?	super	T	,	?	extends	Iterable	<	?	extends	U	>	>	mapper	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	V	>	resultSelector	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	resultSelector	,	"str"	)	;	
return	flatMap	(	ObservableInternalHelper	.	flatMapIntoIterable	(	mapper	)	,	resultSelector	,	false	,	bufferSize	(	)	,	bufferSize	(	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMapMaybe	(	Function	<	?	super	T	,	?	extends	MaybeSource	<	?	extends	R	>	>	mapper	)	{	
return	flatMapMaybe	(	mapper	,	false	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMapMaybe	(	Function	<	?	super	T	,	?	extends	MaybeSource	<	?	extends	R	>	>	mapper	,	boolean	delayErrors	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMapMaybe	<	T	,	R	>	(	this	,	mapper	,	delayErrors	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMapSingle	(	Function	<	?	super	T	,	?	extends	SingleSource	<	?	extends	R	>	>	mapper	)	{	
return	flatMapSingle	(	mapper	,	false	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	flatMapSingle	(	Function	<	?	super	T	,	?	extends	SingleSource	<	?	extends	R	>	>	mapper	,	boolean	delayErrors	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableFlatMapSingle	<	T	,	R	>	(	this	,	mapper	,	delayErrors	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	forEach	(	Consumer	<	?	super	T	>	onNext	)	{	
return	subscribe	(	onNext	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	forEachWhile	(	Predicate	<	?	super	T	>	onNext	)	{	
return	forEachWhile	(	onNext	,	Functions	.	ON_ERROR_MISSING	,	Functions	.	EMPTY_ACTION	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	forEachWhile	(	Predicate	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	)	{	
return	forEachWhile	(	onNext	,	onError	,	Functions	.	EMPTY_ACTION	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	forEachWhile	(	final	Predicate	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	,	
final	Action	onComplete	)	{	
ObjectHelper	.	requireNonNull	(	onNext	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onError	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onComplete	,	"str"	)	;	

ForEachWhileObserver	<	T	>	o	=	new	ForEachWhileObserver	<	T	>	(	onNext	,	onError	,	onComplete	)	;	
subscribe	(	o	)	;	
return	o	;	
}	




























@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Observable	<	GroupedObservable	<	K	,	T	>	>	groupBy	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	)	{	
return	groupBy	(	keySelector	,	(	Function	)	Functions	.	identity	(	)	,	false	,	bufferSize	(	)	)	;	
}	































@SuppressWarnings	(	{	"str"	,	"str"	}	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Observable	<	GroupedObservable	<	K	,	T	>	>	groupBy	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	boolean	delayError	)	{	
return	groupBy	(	keySelector	,	(	Function	)	Functions	.	identity	(	)	,	delayError	,	bufferSize	(	)	)	;	
}	
































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Observable	<	GroupedObservable	<	K	,	V	>	>	groupBy	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
Function	<	?	super	T	,	?	extends	V	>	valueSelector	)	{	
return	groupBy	(	keySelector	,	valueSelector	,	false	,	bufferSize	(	)	)	;	
}	



































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Observable	<	GroupedObservable	<	K	,	V	>	>	groupBy	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
Function	<	?	super	T	,	?	extends	V	>	valueSelector	,	boolean	delayError	)	{	
return	groupBy	(	keySelector	,	valueSelector	,	delayError	,	bufferSize	(	)	)	;	
}	





































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Observable	<	GroupedObservable	<	K	,	V	>	>	groupBy	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
Function	<	?	super	T	,	?	extends	V	>	valueSelector	,	
boolean	delayError	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	valueSelector	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableGroupBy	<	T	,	K	,	V	>	(	this	,	keySelector	,	valueSelector	,	bufferSize	,	delayError	)	)	;	
}	
































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	TRight	,	TLeftEnd	,	TRightEnd	,	R	>	Observable	<	R	>	groupJoin	(	
ObservableSource	<	?	extends	TRight	>	other	,	
Function	<	?	super	T	,	?	extends	ObservableSource	<	TLeftEnd	>	>	leftEnd	,	
Function	<	?	super	TRight	,	?	extends	ObservableSource	<	TRightEnd	>	>	rightEnd	,	
BiFunction	<	?	super	T	,	?	super	Observable	<	TRight	>	,	?	extends	R	>	resultSelector	
)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	leftEnd	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	rightEnd	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	resultSelector	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableGroupJoin	<	T	,	TRight	,	TLeftEnd	,	TRightEnd	,	R	>	(	
this	,	other	,	leftEnd	,	rightEnd	,	resultSelector	)	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	hide	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableHide	<	T	>	(	this	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Completable	ignoreElements	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableIgnoreElementsCompletable	<	T	>	(	this	)	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	Boolean	>	isEmpty	(	)	{	
return	all	(	Functions	.	alwaysFalse	(	)	)	;	
}	
































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	TRight	,	TLeftEnd	,	TRightEnd	,	R	>	Observable	<	R	>	join	(	
ObservableSource	<	?	extends	TRight	>	other	,	
Function	<	?	super	T	,	?	extends	ObservableSource	<	TLeftEnd	>	>	leftEnd	,	
Function	<	?	super	TRight	,	?	extends	ObservableSource	<	TRightEnd	>	>	rightEnd	,	
BiFunction	<	?	super	T	,	?	super	TRight	,	?	extends	R	>	resultSelector	
)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	leftEnd	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	rightEnd	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	resultSelector	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableJoin	<	T	,	TRight	,	TLeftEnd	,	TRightEnd	,	R	>	(	
this	,	other	,	leftEnd	,	rightEnd	,	resultSelector	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Maybe	<	T	>	lastElement	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableLastMaybe	<	T	>	(	this	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	last	(	T	defaultItem	)	{	
ObjectHelper	.	requireNonNull	(	defaultItem	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableLastSingle	<	T	>	(	this	,	defaultItem	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	lastOrError	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableLastSingle	<	T	>	(	this	,	null	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	lift	(	ObservableOperator	<	?	extends	R	,	?	super	T	>	lifter	)	{	
ObjectHelper	.	requireNonNull	(	lifter	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableLift	<	R	,	T	>	(	this	,	lifter	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	map	(	Function	<	?	super	T	,	?	extends	R	>	mapper	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableMap	<	T	,	R	>	(	this	,	mapper	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Notification	<	T	>	>	materialize	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableMaterialize	<	T	>	(	this	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	mergeWith	(	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	merge	(	this	,	other	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	observeOn	(	Scheduler	scheduler	)	{	
return	observeOn	(	scheduler	,	false	,	bufferSize	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	observeOn	(	Scheduler	scheduler	,	boolean	delayError	)	{	
return	observeOn	(	scheduler	,	delayError	,	bufferSize	(	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	observeOn	(	Scheduler	scheduler	,	boolean	delayError	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableObserveOn	<	T	>	(	this	,	scheduler	,	delayError	,	bufferSize	)	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	U	>	ofType	(	final	Class	<	U	>	clazz	)	{	
ObjectHelper	.	requireNonNull	(	clazz	,	"str"	)	;	
return	filter	(	Functions	.	isInstanceOf	(	clazz	)	)	.	cast	(	clazz	)	;	
}	






























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	onErrorResumeNext	(	Function	<	?	super	Throwable	,	?	extends	ObservableSource	<	?	extends	T	>	>	resumeFunction	)	{	
ObjectHelper	.	requireNonNull	(	resumeFunction	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableOnErrorNext	<	T	>	(	this	,	resumeFunction	,	false	)	)	;	
}	






























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	onErrorResumeNext	(	final	ObservableSource	<	?	extends	T	>	next	)	{	
ObjectHelper	.	requireNonNull	(	next	,	"str"	)	;	
return	onErrorResumeNext	(	Functions	.	justFunction	(	next	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	onErrorReturn	(	Function	<	?	super	Throwable	,	?	extends	T	>	valueSupplier	)	{	
ObjectHelper	.	requireNonNull	(	valueSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableOnErrorReturn	<	T	>	(	this	,	valueSupplier	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	onErrorReturnItem	(	final	T	item	)	{	
ObjectHelper	.	requireNonNull	(	item	,	"str"	)	;	
return	onErrorReturn	(	Functions	.	justFunction	(	item	)	)	;	
}	

































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	onExceptionResumeNext	(	final	ObservableSource	<	?	extends	T	>	next	)	{	
ObjectHelper	.	requireNonNull	(	next	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableOnErrorNext	<	T	>	(	this	,	Functions	.	justFunction	(	next	)	,	true	)	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	onTerminateDetach	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableDetach	<	T	>	(	this	)	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	ConnectableObservable	<	T	>	publish	(	)	{	
return	ObservablePublish	.	create	(	this	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	publish	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservablePublishSelector	<	T	,	R	>	(	this	,	selector	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Maybe	<	T	>	reduce	(	BiFunction	<	T	,	T	,	T	>	reducer	)	{	
ObjectHelper	.	requireNonNull	(	reducer	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableReduceMaybe	<	T	>	(	this	,	reducer	)	)	;	
}	















































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Single	<	R	>	reduce	(	R	seed	,	BiFunction	<	R	,	?	super	T	,	R	>	reducer	)	{	
ObjectHelper	.	requireNonNull	(	seed	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	reducer	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableReduceSeedSingle	<	T	,	R	>	(	this	,	seed	,	reducer	)	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Single	<	R	>	reduceWith	(	Callable	<	R	>	seedSupplier	,	BiFunction	<	R	,	?	super	T	,	R	>	reducer	)	{	
ObjectHelper	.	requireNonNull	(	seedSupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	reducer	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableReduceWithSingle	<	T	,	R	>	(	this	,	seedSupplier	,	reducer	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	repeat	(	)	{	
return	repeat	(	Long	.	MAX_VALUE	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	repeat	(	long	times	)	{	
if	(	times	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	+	times	)	;	
}	
if	(	times	=	=	0	)	{	
return	empty	(	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableRepeat	<	T	>	(	this	,	times	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	repeatUntil	(	BooleanSupplier	stop	)	{	
ObjectHelper	.	requireNonNull	(	stop	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableRepeatUntil	<	T	>	(	this	,	stop	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	repeatWhen	(	final	Function	<	?	super	Observable	<	Object	>	,	?	extends	ObservableSource	<	?	>	>	handler	)	{	
ObjectHelper	.	requireNonNull	(	handler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableRepeatWhen	<	T	>	(	this	,	handler	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	ConnectableObservable	<	T	>	replay	(	)	{	
return	ObservableReplay	.	createFrom	(	this	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	replay	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
return	ObservableReplay	.	multicastSelector	(	ObservableInternalHelper	.	replayCallable	(	this	)	,	selector	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	replay	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	final	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	ObservableReplay	.	multicastSelector	(	ObservableInternalHelper	.	replayCallable	(	this	,	bufferSize	)	,	selector	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	<	R	>	Observable	<	R	>	replay	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	int	bufferSize	,	long	time	,	TimeUnit	unit	)	{	
return	replay	(	selector	,	bufferSize	,	time	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	

































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	<	R	>	Observable	<	R	>	replay	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	final	int	bufferSize	,	final	long	time	,	final	TimeUnit	unit	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	ObservableReplay	.	multicastSelector	(	
ObservableInternalHelper	.	replayCallable	(	this	,	bufferSize	,	time	,	unit	,	scheduler	)	,	selector	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	<	R	>	Observable	<	R	>	replay	(	final	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	final	int	bufferSize	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	ObservableReplay	.	multicastSelector	(	ObservableInternalHelper	.	replayCallable	(	this	,	bufferSize	)	,	
ObservableInternalHelper	.	replayFunction	(	selector	,	scheduler	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	<	R	>	Observable	<	R	>	replay	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	long	time	,	TimeUnit	unit	)	{	
return	replay	(	selector	,	time	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	<	R	>	Observable	<	R	>	replay	(	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	final	long	time	,	final	TimeUnit	unit	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	ObservableReplay	.	multicastSelector	(	ObservableInternalHelper	.	replayCallable	(	this	,	time	,	unit	,	scheduler	)	,	selector	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	<	R	>	Observable	<	R	>	replay	(	final	Function	<	?	super	Observable	<	T	>	,	?	extends	ObservableSource	<	R	>	>	selector	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	selector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	ObservableReplay	.	multicastSelector	(	ObservableInternalHelper	.	replayCallable	(	this	)	,	
ObservableInternalHelper	.	replayFunction	(	selector	,	scheduler	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	ConnectableObservable	<	T	>	replay	(	final	int	bufferSize	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	ObservableReplay	.	create	(	this	,	bufferSize	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	ConnectableObservable	<	T	>	replay	(	int	bufferSize	,	long	time	,	TimeUnit	unit	)	{	
return	replay	(	bufferSize	,	time	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	ConnectableObservable	<	T	>	replay	(	final	int	bufferSize	,	final	long	time	,	final	TimeUnit	unit	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	ObservableReplay	.	create	(	this	,	time	,	unit	,	scheduler	,	bufferSize	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	ConnectableObservable	<	T	>	replay	(	final	int	bufferSize	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	ObservableReplay	.	observeOn	(	replay	(	bufferSize	)	,	scheduler	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	ConnectableObservable	<	T	>	replay	(	long	time	,	TimeUnit	unit	)	{	
return	replay	(	time	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	ConnectableObservable	<	T	>	replay	(	final	long	time	,	final	TimeUnit	unit	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	ObservableReplay	.	create	(	this	,	time	,	unit	,	scheduler	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	ConnectableObservable	<	T	>	replay	(	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	ObservableReplay	.	observeOn	(	replay	(	)	,	scheduler	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retry	(	)	{	
return	retry	(	Long	.	MAX_VALUE	,	Functions	.	alwaysTrue	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retry	(	BiPredicate	<	?	super	Integer	,	?	super	Throwable	>	predicate	)	{	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableRetryBiPredicate	<	T	>	(	this	,	predicate	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retry	(	long	times	)	{	
return	retry	(	times	,	Functions	.	alwaysTrue	(	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retry	(	long	times	,	Predicate	<	?	super	Throwable	>	predicate	)	{	
if	(	times	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	+	times	)	;	
}	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableRetryPredicate	<	T	>	(	this	,	times	,	predicate	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retry	(	Predicate	<	?	super	Throwable	>	predicate	)	{	
return	retry	(	Long	.	MAX_VALUE	,	predicate	)	;	
}	











@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retryUntil	(	final	BooleanSupplier	stop	)	{	
ObjectHelper	.	requireNonNull	(	stop	,	"str"	)	;	
return	retry	(	Long	.	MAX_VALUE	,	Functions	.	predicateReverseFor	(	stop	)	)	;	
}	










































































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	retryWhen	(	
final	Function	<	?	super	Observable	<	Throwable	>	,	?	extends	ObservableSource	<	?	>	>	handler	)	{	
ObjectHelper	.	requireNonNull	(	handler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableRetryWhen	<	T	>	(	this	,	handler	)	)	;	
}	













@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	void	safeSubscribe	(	Observer	<	?	super	T	>	s	)	{	
ObjectHelper	.	requireNonNull	(	s	,	"str"	)	;	
if	(	s	instanceof	SafeObserver	)	{	
subscribe	(	s	)	;	
}	else	{	
subscribe	(	new	SafeObserver	<	T	>	(	s	)	)	;	
}	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	sample	(	long	period	,	TimeUnit	unit	)	{	
return	sample	(	period	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	sample	(	long	period	,	TimeUnit	unit	,	boolean	emitLast	)	{	
return	sample	(	period	,	unit	,	Schedulers	.	computation	(	)	,	emitLast	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	sample	(	long	period	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSampleTimed	<	T	>	(	this	,	period	,	unit	,	scheduler	,	false	)	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	sample	(	long	period	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	emitLast	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSampleTimed	<	T	>	(	this	,	period	,	unit	,	scheduler	,	emitLast	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	sample	(	ObservableSource	<	U	>	sampler	)	{	
ObjectHelper	.	requireNonNull	(	sampler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSampleWithObservable	<	T	>	(	this	,	sampler	,	false	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	sample	(	ObservableSource	<	U	>	sampler	,	boolean	emitLast	)	{	
ObjectHelper	.	requireNonNull	(	sampler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSampleWithObservable	<	T	>	(	this	,	sampler	,	emitLast	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	scan	(	BiFunction	<	T	,	T	,	T	>	accumulator	)	{	
ObjectHelper	.	requireNonNull	(	accumulator	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableScan	<	T	>	(	this	,	accumulator	)	)	;	
}	











































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	scan	(	final	R	initialValue	,	BiFunction	<	R	,	?	super	T	,	R	>	accumulator	)	{	
ObjectHelper	.	requireNonNull	(	initialValue	,	"str"	)	;	
return	scanWith	(	Functions	.	justCallable	(	initialValue	)	,	accumulator	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	scanWith	(	Callable	<	R	>	seedSupplier	,	BiFunction	<	R	,	?	super	T	,	R	>	accumulator	)	{	
ObjectHelper	.	requireNonNull	(	seedSupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	accumulator	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableScanSeed	<	T	,	R	>	(	this	,	seedSupplier	,	accumulator	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	serialize	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSerialized	<	T	>	(	this	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	share	(	)	{	
return	publish	(	)	.	refCount	(	)	;	
}	














@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Maybe	<	T	>	singleElement	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSingleMaybe	<	T	>	(	this	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	single	(	T	defaultItem	)	{	
ObjectHelper	.	requireNonNull	(	defaultItem	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSingleSingle	<	T	>	(	this	,	defaultItem	)	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	T	>	singleOrError	(	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSingleSingle	<	T	>	(	this	,	null	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	skip	(	long	count	)	{	
if	(	count	<	=	0	)	{	
return	RxJavaPlugins	.	onAssembly	(	this	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSkip	<	T	>	(	this	,	count	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	skip	(	long	time	,	TimeUnit	unit	)	{	
return	skipUntil	(	timer	(	time	,	unit	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	skip	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	skipUntil	(	timer	(	time	,	unit	,	scheduler	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	skipLast	(	int	count	)	{	
if	(	count	<	0	)	{	
throw	new	IndexOutOfBoundsException	(	"str"	+	count	)	;	
}	
if	(	count	=	=	0	)	{	
return	RxJavaPlugins	.	onAssembly	(	this	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSkipLast	<	T	>	(	this	,	count	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	TRAMPOLINE	)	
public	final	Observable	<	T	>	skipLast	(	long	time	,	TimeUnit	unit	)	{	
return	skipLast	(	time	,	unit	,	Schedulers	.	trampoline	(	)	,	false	,	bufferSize	(	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	TRAMPOLINE	)	
public	final	Observable	<	T	>	skipLast	(	long	time	,	TimeUnit	unit	,	boolean	delayError	)	{	
return	skipLast	(	time	,	unit	,	Schedulers	.	trampoline	(	)	,	delayError	,	bufferSize	(	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	skipLast	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	skipLast	(	time	,	unit	,	scheduler	,	false	,	bufferSize	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	skipLast	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	delayError	)	{	
return	skipLast	(	time	,	unit	,	scheduler	,	delayError	,	bufferSize	(	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	skipLast	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	delayError	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
int	s	=	bufferSize	<	<	1	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSkipLastTimed	<	T	>	(	this	,	time	,	unit	,	scheduler	,	s	,	delayError	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	skipUntil	(	ObservableSource	<	U	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSkipUntil	<	T	,	U	>	(	this	,	other	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	skipWhile	(	Predicate	<	?	super	T	>	predicate	)	{	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSkipWhile	<	T	>	(	this	,	predicate	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	sorted	(	)	{	
return	toList	(	)	.	toObservable	(	)	.	map	(	Functions	.	listSorter	(	Functions	.	<	T	>	naturalComparator	(	)	)	)	.	flatMapIterable	(	Functions	.	<	List	<	T	>	>	identity	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	sorted	(	Comparator	<	?	super	T	>	sortFunction	)	{	
ObjectHelper	.	requireNonNull	(	sortFunction	,	"str"	)	;	
return	toList	(	)	.	toObservable	(	)	.	map	(	Functions	.	listSorter	(	sortFunction	)	)	.	flatMapIterable	(	Functions	.	<	List	<	T	>	>	identity	(	)	)	;	
}	

















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	startWith	(	Iterable	<	?	extends	T	>	items	)	{	
return	concatArray	(	fromIterable	(	items	)	,	this	)	;	
}	

















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	startWith	(	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	concatArray	(	other	,	this	)	;	
}	

















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	startWith	(	T	item	)	{	
ObjectHelper	.	requireNonNull	(	item	,	"str"	)	;	
return	concatArray	(	just	(	item	)	,	this	)	;	
}	

















@SuppressWarnings	(	"str"	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	startWithArray	(	T	.	.	.	items	)	{	
Observable	<	T	>	fromArray	=	fromArray	(	items	)	;	
if	(	fromArray	=	=	empty	(	)	)	{	
return	RxJavaPlugins	.	onAssembly	(	this	)	;	
}	
return	concatArray	(	fromArray	,	this	)	;	
}	
















@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	subscribe	(	)	{	
return	subscribe	(	Functions	.	emptyConsumer	(	)	,	Functions	.	ON_ERROR_MISSING	,	Functions	.	EMPTY_ACTION	,	Functions	.	emptyConsumer	(	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	subscribe	(	Consumer	<	?	super	T	>	onNext	)	{	
return	subscribe	(	onNext	,	Functions	.	ON_ERROR_MISSING	,	Functions	.	EMPTY_ACTION	,	Functions	.	emptyConsumer	(	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	subscribe	(	Consumer	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	)	{	
return	subscribe	(	onNext	,	onError	,	Functions	.	EMPTY_ACTION	,	Functions	.	emptyConsumer	(	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	subscribe	(	Consumer	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	,	
Action	onComplete	)	{	
return	subscribe	(	onNext	,	onError	,	onComplete	,	Functions	.	emptyConsumer	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Disposable	subscribe	(	Consumer	<	?	super	T	>	onNext	,	Consumer	<	?	super	Throwable	>	onError	,	
Action	onComplete	,	Consumer	<	?	super	Disposable	>	onSubscribe	)	{	
ObjectHelper	.	requireNonNull	(	onNext	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onError	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onComplete	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	onSubscribe	,	"str"	)	;	

LambdaObserver	<	T	>	ls	=	new	LambdaObserver	<	T	>	(	onNext	,	onError	,	onComplete	,	onSubscribe	)	;	

subscribe	(	ls	)	;	

return	ls	;	
}	

@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@Override	
public	final	void	subscribe	(	Observer	<	?	super	T	>	observer	)	{	
ObjectHelper	.	requireNonNull	(	observer	,	"str"	)	;	
try	{	
observer	=	RxJavaPlugins	.	onSubscribe	(	this	,	observer	)	;	

ObjectHelper	.	requireNonNull	(	observer	,	"str"	)	;	

subscribeActual	(	observer	)	;	
}	catch	(	NullPointerException	e	)	{	throw	e	;	
}	catch	(	Throwable	e	)	{	
Exceptions	.	throwIfFatal	(	e	)	;	
RxJavaPlugins	.	onError	(	e	)	;	

NullPointerException	npe	=	new	NullPointerException	(	"str"	)	;	
npe	.	initCause	(	e	)	;	
throw	npe	;	
}	
}	








protected	abstract	void	subscribeActual	(	Observer	<	?	super	T	>	observer	)	;	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	E	extends	Observer	<	?	super	T	>	>	E	subscribeWith	(	E	observer	)	{	
subscribe	(	observer	)	;	
return	observer	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	subscribeOn	(	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSubscribeOn	<	T	>	(	this	,	scheduler	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	switchIfEmpty	(	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSwitchIfEmpty	<	T	>	(	this	,	other	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	switchMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	)	{	
return	switchMap	(	mapper	,	bufferSize	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	switchMap	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
if	(	this	instanceof	ScalarCallable	)	{	
@SuppressWarnings	(	"str"	)	
T	v	=	(	(	ScalarCallable	<	T	>	)	this	)	.	call	(	)	;	
if	(	v	=	=	null	)	{	
return	empty	(	)	;	
}	
return	ObservableScalarXMap	.	scalarXMap	(	v	,	mapper	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSwitchMap	<	T	,	R	>	(	this	,	mapper	,	bufferSize	,	false	)	)	;	
}	























@Experimental	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@NonNull	
public	final	<	R	>	Observable	<	R	>	switchMapSingle	(	@NonNull	Function	<	?	super	T	,	?	extends	SingleSource	<	?	extends	R	>	>	mapper	)	{	
return	ObservableInternalHelper	.	switchMapSingle	(	this	,	mapper	)	;	
}	
























@Experimental	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
@NonNull	
public	final	<	R	>	Observable	<	R	>	switchMapSingleDelayError	(	@NonNull	Function	<	?	super	T	,	?	extends	SingleSource	<	?	extends	R	>	>	mapper	)	{	
return	ObservableInternalHelper	.	switchMapSingleDelayError	(	this	,	mapper	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	switchMapDelayError	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	)	{	
return	switchMapDelayError	(	mapper	,	bufferSize	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	switchMapDelayError	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	?	extends	R	>	>	mapper	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	mapper	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
if	(	this	instanceof	ScalarCallable	)	{	
@SuppressWarnings	(	"str"	)	
T	v	=	(	(	ScalarCallable	<	T	>	)	this	)	.	call	(	)	;	
if	(	v	=	=	null	)	{	
return	empty	(	)	;	
}	
return	ObservableScalarXMap	.	scalarXMap	(	v	,	mapper	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableSwitchMap	<	T	,	R	>	(	this	,	mapper	,	bufferSize	,	true	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	take	(	long	count	)	{	
if	(	count	<	0	)	{	
throw	new	IllegalArgumentException	(	"str"	+	count	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTake	<	T	>	(	this	,	count	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	take	(	long	time	,	TimeUnit	unit	)	{	
return	takeUntil	(	timer	(	time	,	unit	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	take	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	takeUntil	(	timer	(	time	,	unit	,	scheduler	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	takeLast	(	int	count	)	{	
if	(	count	<	0	)	{	
throw	new	IndexOutOfBoundsException	(	"str"	+	count	)	;	
}	else	
if	(	count	=	=	0	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableIgnoreElements	<	T	>	(	this	)	)	;	
}	else	
if	(	count	=	=	1	)	{	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTakeLastOne	<	T	>	(	this	)	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTakeLast	<	T	>	(	this	,	count	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	TRAMPOLINE	)	
public	final	Observable	<	T	>	takeLast	(	long	count	,	long	time	,	TimeUnit	unit	)	{	
return	takeLast	(	count	,	time	,	unit	,	Schedulers	.	trampoline	(	)	,	false	,	bufferSize	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	takeLast	(	long	count	,	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	takeLast	(	count	,	time	,	unit	,	scheduler	,	false	,	bufferSize	(	)	)	;	
}	
































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	takeLast	(	long	count	,	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	delayError	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
if	(	count	<	0	)	{	
throw	new	IndexOutOfBoundsException	(	"str"	+	count	)	;	
}	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTakeLastTimed	<	T	>	(	this	,	count	,	time	,	unit	,	scheduler	,	bufferSize	,	delayError	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	TRAMPOLINE	)	
public	final	Observable	<	T	>	takeLast	(	long	time	,	TimeUnit	unit	)	{	
return	takeLast	(	time	,	unit	,	Schedulers	.	trampoline	(	)	,	false	,	bufferSize	(	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	TRAMPOLINE	)	
public	final	Observable	<	T	>	takeLast	(	long	time	,	TimeUnit	unit	,	boolean	delayError	)	{	
return	takeLast	(	time	,	unit	,	Schedulers	.	trampoline	(	)	,	delayError	,	bufferSize	(	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	takeLast	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	takeLast	(	time	,	unit	,	scheduler	,	false	,	bufferSize	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	takeLast	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	delayError	)	{	
return	takeLast	(	time	,	unit	,	scheduler	,	delayError	,	bufferSize	(	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	takeLast	(	long	time	,	TimeUnit	unit	,	Scheduler	scheduler	,	boolean	delayError	,	int	bufferSize	)	{	
return	takeLast	(	Long	.	MAX_VALUE	,	time	,	unit	,	scheduler	,	delayError	,	bufferSize	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	>	Observable	<	T	>	takeUntil	(	ObservableSource	<	U	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTakeUntil	<	T	,	U	>	(	this	,	other	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	takeUntil	(	Predicate	<	?	super	T	>	stopPredicate	)	{	
ObjectHelper	.	requireNonNull	(	stopPredicate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTakeUntilPredicate	<	T	>	(	this	,	stopPredicate	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	T	>	takeWhile	(	Predicate	<	?	super	T	>	predicate	)	{	
ObjectHelper	.	requireNonNull	(	predicate	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTakeWhile	<	T	>	(	this	,	predicate	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	throttleFirst	(	long	windowDuration	,	TimeUnit	unit	)	{	
return	throttleFirst	(	windowDuration	,	unit	,	Schedulers	.	computation	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	throttleFirst	(	long	skipDuration	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableThrottleFirstTimed	<	T	>	(	this	,	skipDuration	,	unit	,	scheduler	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	throttleLast	(	long	intervalDuration	,	TimeUnit	unit	)	{	
return	sample	(	intervalDuration	,	unit	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	throttleLast	(	long	intervalDuration	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	sample	(	intervalDuration	,	unit	,	scheduler	)	;	
}	































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	throttleWithTimeout	(	long	timeout	,	TimeUnit	unit	)	{	
return	debounce	(	timeout	,	unit	)	;	
}	



































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	throttleWithTimeout	(	long	timeout	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	debounce	(	timeout	,	unit	,	scheduler	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Timed	<	T	>	>	timeInterval	(	)	{	
return	timeInterval	(	TimeUnit	.	MILLISECONDS	,	Schedulers	.	computation	(	)	)	;	
}	

















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	public	final	Observable	<	Timed	<	T	>	>	timeInterval	(	Scheduler	scheduler	)	{	
return	timeInterval	(	TimeUnit	.	MILLISECONDS	,	scheduler	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	public	final	Observable	<	Timed	<	T	>	>	timeInterval	(	TimeUnit	unit	)	{	
return	timeInterval	(	unit	,	Schedulers	.	computation	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	public	final	Observable	<	Timed	<	T	>	>	timeInterval	(	TimeUnit	unit	,	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTimeInterval	<	T	>	(	this	,	unit	,	scheduler	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	V	>	Observable	<	T	>	timeout	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	V	>	>	itemTimeoutIndicator	)	{	
return	timeout0	(	null	,	itemTimeoutIndicator	,	null	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	V	>	Observable	<	T	>	timeout	(	Function	<	?	super	T	,	?	extends	ObservableSource	<	V	>	>	itemTimeoutIndicator	,	
ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	timeout0	(	null	,	itemTimeoutIndicator	,	other	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	timeout	(	long	timeout	,	TimeUnit	timeUnit	)	{	
return	timeout0	(	timeout	,	timeUnit	,	null	,	Schedulers	.	computation	(	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	T	>	timeout	(	long	timeout	,	TimeUnit	timeUnit	,	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	timeout0	(	timeout	,	timeUnit	,	other	,	Schedulers	.	computation	(	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	timeout	(	long	timeout	,	TimeUnit	timeUnit	,	Scheduler	scheduler	,	ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	timeout0	(	timeout	,	timeUnit	,	other	,	scheduler	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	timeout	(	long	timeout	,	TimeUnit	timeUnit	,	Scheduler	scheduler	)	{	
return	timeout0	(	timeout	,	timeUnit	,	null	,	scheduler	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	V	>	Observable	<	T	>	timeout	(	ObservableSource	<	U	>	firstTimeoutIndicator	,	
Function	<	?	super	T	,	?	extends	ObservableSource	<	V	>	>	itemTimeoutIndicator	)	{	
ObjectHelper	.	requireNonNull	(	firstTimeoutIndicator	,	"str"	)	;	
return	timeout0	(	firstTimeoutIndicator	,	itemTimeoutIndicator	,	null	)	;	
}	

































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	V	>	Observable	<	T	>	timeout	(	
ObservableSource	<	U	>	firstTimeoutIndicator	,	
Function	<	?	super	T	,	?	extends	ObservableSource	<	V	>	>	itemTimeoutIndicator	,	
ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	firstTimeoutIndicator	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	timeout0	(	firstTimeoutIndicator	,	itemTimeoutIndicator	,	other	)	;	
}	

private	Observable	<	T	>	timeout0	(	long	timeout	,	TimeUnit	timeUnit	,	ObservableSource	<	?	extends	T	>	other	,	
Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	timeUnit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTimeoutTimed	<	T	>	(	this	,	timeout	,	timeUnit	,	scheduler	,	other	)	)	;	
}	

private	<	U	,	V	>	Observable	<	T	>	timeout0	(	
ObservableSource	<	U	>	firstTimeoutIndicator	,	
Function	<	?	super	T	,	?	extends	ObservableSource	<	V	>	>	itemTimeoutIndicator	,	
ObservableSource	<	?	extends	T	>	other	)	{	
ObjectHelper	.	requireNonNull	(	itemTimeoutIndicator	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableTimeout	<	T	,	U	,	V	>	(	this	,	firstTimeoutIndicator	,	itemTimeoutIndicator	,	other	)	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Timed	<	T	>	>	timestamp	(	)	{	
return	timestamp	(	TimeUnit	.	MILLISECONDS	,	Schedulers	.	computation	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	public	final	Observable	<	Timed	<	T	>	>	timestamp	(	Scheduler	scheduler	)	{	
return	timestamp	(	TimeUnit	.	MILLISECONDS	,	scheduler	)	;	
}	
















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Timed	<	T	>	>	timestamp	(	TimeUnit	unit	)	{	
return	timestamp	(	unit	,	Schedulers	.	computation	(	)	)	;	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	public	final	Observable	<	Timed	<	T	>	>	timestamp	(	final	TimeUnit	unit	,	final	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	map	(	Functions	.	<	T	>	timestampWith	(	unit	,	scheduler	)	)	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	R	to	(	Function	<	?	super	Observable	<	T	>	,	R	>	converter	)	{	
try	{	
return	ObjectHelper	.	requireNonNull	(	converter	,	"str"	)	.	apply	(	this	)	;	
}	catch	(	Throwable	ex	)	{	
Exceptions	.	throwIfFatal	(	ex	)	;	
throw	ExceptionHelper	.	wrapOrThrow	(	ex	)	;	
}	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	List	<	T	>	>	toList	(	)	{	
return	toList	(	16	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	List	<	T	>	>	toList	(	final	int	capacityHint	)	{	
ObjectHelper	.	verifyPositive	(	capacityHint	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableToListSingle	<	T	,	List	<	T	>	>	(	this	,	capacityHint	)	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	extends	Collection	<	?	super	T	>	>	Single	<	U	>	toList	(	Callable	<	U	>	collectionSupplier	)	{	
ObjectHelper	.	requireNonNull	(	collectionSupplier	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableToListSingle	<	T	,	U	>	(	this	,	collectionSupplier	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Single	<	Map	<	K	,	T	>	>	toMap	(	final	Function	<	?	super	T	,	?	extends	K	>	keySelector	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
return	collect	(	HashMapSupplier	.	<	K	,	T	>	asCallable	(	)	,	Functions	.	toMapKeySelector	(	keySelector	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Single	<	Map	<	K	,	V	>	>	toMap	(	
final	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
final	Function	<	?	super	T	,	?	extends	V	>	valueSelector	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	valueSelector	,	"str"	)	;	
return	collect	(	HashMapSupplier	.	<	K	,	V	>	asCallable	(	)	,	Functions	.	toMapKeyValueSelector	(	keySelector	,	valueSelector	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Single	<	Map	<	K	,	V	>	>	toMap	(	
final	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
final	Function	<	?	super	T	,	?	extends	V	>	valueSelector	,	
Callable	<	?	extends	Map	<	K	,	V	>	>	mapSupplier	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	valueSelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	mapSupplier	,	"str"	)	;	
return	collect	(	mapSupplier	,	Functions	.	toMapKeyValueSelector	(	keySelector	,	valueSelector	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	>	Single	<	Map	<	K	,	Collection	<	T	>	>	>	toMultimap	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	)	{	
@SuppressWarnings	(	{	"str"	,	"str"	}	)	
Function	<	?	super	T	,	?	extends	T	>	valueSelector	=	(	Function	)	Functions	.	identity	(	)	;	
Callable	<	Map	<	K	,	Collection	<	T	>	>	>	mapSupplier	=	HashMapSupplier	.	asCallable	(	)	;	
Function	<	K	,	List	<	T	>	>	collectionFactory	=	ArrayListSupplier	.	asFunction	(	)	;	
return	toMultimap	(	keySelector	,	valueSelector	,	mapSupplier	,	collectionFactory	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Single	<	Map	<	K	,	Collection	<	V	>	>	>	toMultimap	(	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	Function	<	?	super	T	,	?	extends	V	>	valueSelector	)	{	
Callable	<	Map	<	K	,	Collection	<	V	>	>	>	mapSupplier	=	HashMapSupplier	.	asCallable	(	)	;	
Function	<	K	,	List	<	V	>	>	collectionFactory	=	ArrayListSupplier	.	asFunction	(	)	;	
return	toMultimap	(	keySelector	,	valueSelector	,	mapSupplier	,	collectionFactory	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Single	<	Map	<	K	,	Collection	<	V	>	>	>	toMultimap	(	
final	Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
final	Function	<	?	super	T	,	?	extends	V	>	valueSelector	,	
final	Callable	<	?	extends	Map	<	K	,	Collection	<	V	>	>	>	mapSupplier	,	
final	Function	<	?	super	K	,	?	extends	Collection	<	?	super	V	>	>	collectionFactory	)	{	
ObjectHelper	.	requireNonNull	(	keySelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	valueSelector	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	mapSupplier	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	collectionFactory	,	"str"	)	;	
return	collect	(	mapSupplier	,	Functions	.	toMultimapKeyValueSelector	(	keySelector	,	valueSelector	,	collectionFactory	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	K	,	V	>	Single	<	Map	<	K	,	Collection	<	V	>	>	>	toMultimap	(	
Function	<	?	super	T	,	?	extends	K	>	keySelector	,	
Function	<	?	super	T	,	?	extends	V	>	valueSelector	,	
Callable	<	Map	<	K	,	Collection	<	V	>	>	>	mapSupplier	
)	{	
return	toMultimap	(	keySelector	,	valueSelector	,	mapSupplier	,	ArrayListSupplier	.	<	V	,	K	>	asFunction	(	)	)	;	
}	













@BackpressureSupport	(	BackpressureKind	.	SPECIAL	)	
@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Flowable	<	T	>	toFlowable	(	BackpressureStrategy	strategy	)	{	
Flowable	<	T	>	o	=	new	FlowableFromObservable	<	T	>	(	this	)	;	

switch	(	strategy	)	{	
case	DROP	:	
return	o	.	onBackpressureDrop	(	)	;	
case	LATEST	:	
return	o	.	onBackpressureLatest	(	)	;	
case	MISSING	:	
return	o	;	
case	ERROR	:	
return	RxJavaPlugins	.	onAssembly	(	new	FlowableOnBackpressureError	<	T	>	(	o	)	)	;	
default	:	
return	o	.	onBackpressureBuffer	(	)	;	
}	
}	



















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	List	<	T	>	>	toSortedList	(	)	{	
return	toSortedList	(	Functions	.	naturalOrder	(	)	)	;	
}	


















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	List	<	T	>	>	toSortedList	(	final	Comparator	<	?	super	T	>	comparator	)	{	
ObjectHelper	.	requireNonNull	(	comparator	,	"str"	)	;	
return	toList	(	)	.	map	(	Functions	.	listSorter	(	comparator	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	List	<	T	>	>	toSortedList	(	final	Comparator	<	?	super	T	>	comparator	,	int	capacityHint	)	{	
ObjectHelper	.	requireNonNull	(	comparator	,	"str"	)	;	
return	toList	(	capacityHint	)	.	map	(	Functions	.	listSorter	(	comparator	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Single	<	List	<	T	>	>	toSortedList	(	int	capacityHint	)	{	
return	toSortedList	(	Functions	.	<	T	>	naturalOrder	(	)	,	capacityHint	)	;	
}	















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	T	>	unsubscribeOn	(	Scheduler	scheduler	)	{	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableUnsubscribeOn	<	T	>	(	this	,	scheduler	)	)	;	
}	




















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	count	)	{	
return	window	(	count	,	count	,	bufferSize	(	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	count	,	long	skip	)	{	
return	window	(	count	,	skip	,	bufferSize	(	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	count	,	long	skip	,	int	bufferSize	)	{	
ObjectHelper	.	verifyPositive	(	count	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	skip	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWindow	<	T	>	(	this	,	count	,	skip	,	bufferSize	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	long	timeskip	,	TimeUnit	unit	)	{	
return	window	(	timespan	,	timeskip	,	unit	,	Schedulers	.	computation	(	)	,	bufferSize	(	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	long	timeskip	,	TimeUnit	unit	,	Scheduler	scheduler	)	{	
return	window	(	timespan	,	timeskip	,	unit	,	scheduler	,	bufferSize	(	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	long	timeskip	,	TimeUnit	unit	,	Scheduler	scheduler	,	int	bufferSize	)	{	
ObjectHelper	.	verifyPositive	(	timespan	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	timeskip	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWindowTimed	<	T	>	(	this	,	timespan	,	timeskip	,	unit	,	scheduler	,	Long	.	MAX_VALUE	,	bufferSize	,	false	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	TimeUnit	unit	)	{	
return	window	(	timespan	,	unit	,	Schedulers	.	computation	(	)	,	Long	.	MAX_VALUE	,	false	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	TimeUnit	unit	,	
long	count	)	{	
return	window	(	timespan	,	unit	,	Schedulers	.	computation	(	)	,	count	,	false	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	COMPUTATION	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	TimeUnit	unit	,	
long	count	,	boolean	restart	)	{	
return	window	(	timespan	,	unit	,	Schedulers	.	computation	(	)	,	count	,	restart	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	TimeUnit	unit	,	
Scheduler	scheduler	)	{	
return	window	(	timespan	,	unit	,	scheduler	,	Long	.	MAX_VALUE	,	false	)	;	
}	




























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	TimeUnit	unit	,	
Scheduler	scheduler	,	long	count	)	{	
return	window	(	timespan	,	unit	,	scheduler	,	count	,	false	)	;	
}	






























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	long	timespan	,	TimeUnit	unit	,	
Scheduler	scheduler	,	long	count	,	boolean	restart	)	{	
return	window	(	timespan	,	unit	,	scheduler	,	count	,	restart	,	bufferSize	(	)	)	;	
}	
































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	CUSTOM	)	
public	final	Observable	<	Observable	<	T	>	>	window	(	
long	timespan	,	TimeUnit	unit	,	Scheduler	scheduler	,	
long	count	,	boolean	restart	,	int	bufferSize	)	{	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	scheduler	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	unit	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	count	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWindowTimed	<	T	>	(	this	,	timespan	,	timespan	,	unit	,	scheduler	,	count	,	bufferSize	,	restart	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	Observable	<	T	>	>	window	(	ObservableSource	<	B	>	boundary	)	{	
return	window	(	boundary	,	bufferSize	(	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	Observable	<	T	>	>	window	(	ObservableSource	<	B	>	boundary	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	boundary	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWindowBoundary	<	T	,	B	>	(	this	,	boundary	,	bufferSize	)	)	;	
}	
























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	V	>	Observable	<	Observable	<	T	>	>	window	(	
ObservableSource	<	U	>	openingIndicator	,	
Function	<	?	super	U	,	?	extends	ObservableSource	<	V	>	>	closingIndicator	)	{	
return	window	(	openingIndicator	,	closingIndicator	,	bufferSize	(	)	)	;	
}	


























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	V	>	Observable	<	Observable	<	T	>	>	window	(	
ObservableSource	<	U	>	openingIndicator	,	
Function	<	?	super	U	,	?	extends	ObservableSource	<	V	>	>	closingIndicator	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	openingIndicator	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	closingIndicator	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWindowBoundarySelector	<	T	,	U	,	V	>	(	this	,	openingIndicator	,	closingIndicator	,	bufferSize	)	)	;	
}	





















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	Observable	<	T	>	>	window	(	Callable	<	?	extends	ObservableSource	<	B	>	>	boundary	)	{	
return	window	(	boundary	,	bufferSize	(	)	)	;	
}	























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	B	>	Observable	<	Observable	<	T	>	>	window	(	Callable	<	?	extends	ObservableSource	<	B	>	>	boundary	,	int	bufferSize	)	{	
ObjectHelper	.	requireNonNull	(	boundary	,	"str"	)	;	
ObjectHelper	.	verifyPositive	(	bufferSize	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWindowBoundarySupplier	<	T	,	B	>	(	this	,	boundary	,	bufferSize	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	withLatestFrom	(	ObservableSource	<	?	extends	U	>	other	,	BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	

return	RxJavaPlugins	.	onAssembly	(	new	ObservableWithLatestFrom	<	T	,	U	,	R	>	(	this	,	combiner	,	other	)	)	;	
}	

























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	T1	,	T2	,	R	>	Observable	<	R	>	withLatestFrom	(	
ObservableSource	<	T1	>	o1	,	ObservableSource	<	T2	>	o2	,	
Function3	<	?	super	T	,	?	super	T1	,	?	super	T2	,	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	o1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	o2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
Function	<	Object	[	]	,	R	>	f	=	Functions	.	toFunction	(	combiner	)	;	
return	withLatestFrom	(	new	ObservableSource	[	]	{	o1	,	o2	}	,	f	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	T1	,	T2	,	T3	,	R	>	Observable	<	R	>	withLatestFrom	(	
ObservableSource	<	T1	>	o1	,	ObservableSource	<	T2	>	o2	,	
ObservableSource	<	T3	>	o3	,	
Function4	<	?	super	T	,	?	super	T1	,	?	super	T2	,	?	super	T3	,	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	o1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	o2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	o3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
Function	<	Object	[	]	,	R	>	f	=	Functions	.	toFunction	(	combiner	)	;	
return	withLatestFrom	(	new	ObservableSource	[	]	{	o1	,	o2	,	o3	}	,	f	)	;	
}	





























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	T1	,	T2	,	T3	,	T4	,	R	>	Observable	<	R	>	withLatestFrom	(	
ObservableSource	<	T1	>	o1	,	ObservableSource	<	T2	>	o2	,	
ObservableSource	<	T3	>	o3	,	ObservableSource	<	T4	>	o4	,	
Function5	<	?	super	T	,	?	super	T1	,	?	super	T2	,	?	super	T3	,	?	super	T4	,	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	o1	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	o2	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	o3	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	o4	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
Function	<	Object	[	]	,	R	>	f	=	Functions	.	toFunction	(	combiner	)	;	
return	withLatestFrom	(	new	ObservableSource	[	]	{	o1	,	o2	,	o3	,	o4	}	,	f	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	withLatestFrom	(	ObservableSource	<	?	>	[	]	others	,	Function	<	?	super	Object	[	]	,	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	others	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWithLatestFromMany	<	T	,	R	>	(	this	,	others	,	combiner	)	)	;	
}	






















@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	R	>	Observable	<	R	>	withLatestFrom	(	Iterable	<	?	extends	ObservableSource	<	?	>	>	others	,	Function	<	?	super	Object	[	]	,	R	>	combiner	)	{	
ObjectHelper	.	requireNonNull	(	others	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	combiner	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableWithLatestFromMany	<	T	,	R	>	(	this	,	others	,	combiner	)	)	;	
}	



























@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	zipWith	(	Iterable	<	U	>	other	,	BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
ObjectHelper	.	requireNonNull	(	zipper	,	"str"	)	;	
return	RxJavaPlugins	.	onAssembly	(	new	ObservableZipIterable	<	T	,	U	,	R	>	(	this	,	other	,	zipper	)	)	;	
}	




































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	zipWith	(	ObservableSource	<	?	extends	U	>	other	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	zipper	)	{	
ObjectHelper	.	requireNonNull	(	other	,	"str"	)	;	
return	zip	(	this	,	other	,	zipper	)	;	
}	







































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	zipWith	(	ObservableSource	<	?	extends	U	>	other	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	zipper	,	boolean	delayError	)	{	
return	zip	(	this	,	other	,	zipper	,	delayError	)	;	
}	









































@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	<	U	,	R	>	Observable	<	R	>	zipWith	(	ObservableSource	<	?	extends	U	>	other	,	
BiFunction	<	?	super	T	,	?	super	U	,	?	extends	R	>	zipper	,	boolean	delayError	,	int	bufferSize	)	{	
return	zip	(	this	,	other	,	zipper	,	delayError	,	bufferSize	)	;	
}	











@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	TestObserver	<	T	>	test	(	)	{	TestObserver	<	T	>	ts	=	new	TestObserver	<	T	>	(	)	;	
subscribe	(	ts	)	;	
return	ts	;	
}	













@CheckReturnValue	
@SchedulerSupport	(	SchedulerSupport	.	NONE	)	
public	final	TestObserver	<	T	>	test	(	boolean	dispose	)	{	TestObserver	<	T	>	ts	=	new	TestObserver	<	T	>	(	)	;	
if	(	dispose	)	{	
ts	.	dispose	(	)	;	
}	
subscribe	(	ts	)	;	
return	ts	;	
}	
}	
