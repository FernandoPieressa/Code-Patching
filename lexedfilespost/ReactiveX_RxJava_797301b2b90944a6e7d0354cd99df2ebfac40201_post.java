












package	io	.	reactivex	.	observers	;	

import static	org	.	junit	.	Assert	.	*	;	
import static	org	.	mockito	.	Mockito	.	*	;	

import	java	.	io	.	IOException	;	
import	java	.	util	.	*	;	
import	java	.	util	.	concurrent	.	TimeUnit	;	

import	org	.	junit	.	*	;	
import	org	.	junit	.	rules	.	ExpectedException	;	
import	org	.	mockito	.	InOrder	;	
import	org	.	reactivestreams	.	Subscriber	;	

import	io	.	reactivex	.	*	;	
import	io	.	reactivex	.	Observable	;	
import	io	.	reactivex	.	Observer	;	
import	io	.	reactivex	.	disposables	.	*	;	
import	io	.	reactivex	.	exceptions	.	TestException	;	
import	io	.	reactivex	.	functions	.	*	;	
import	io	.	reactivex	.	internal	.	functions	.	Functions	;	
import	io	.	reactivex	.	internal	.	fuseable	.	QueueDisposable	;	
import	io	.	reactivex	.	internal	.	operators	.	observable	.	ObservableScalarXMap	.	ScalarDisposable	;	
import	io	.	reactivex	.	internal	.	subscriptions	.	EmptySubscription	;	
import	io	.	reactivex	.	processors	.	PublishProcessor	;	
import	io	.	reactivex	.	schedulers	.	Schedulers	;	
import	io	.	reactivex	.	subjects	.	UnicastSubject	;	
import	io	.	reactivex	.	subscribers	.	TestSubscriber	;	

public	class	TestObserverTest	{	

@Rule	
public	ExpectedException	thrown	=	ExpectedException	.	none	(	)	;	

@Test	
public	void	testAssert	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	)	)	;	
TestSubscriber	<	Integer	>	o	=	new	TestSubscriber	<	Integer	>	(	)	;	
oi	.	subscribe	(	o	)	;	

o	.	assertValues	(	1	,	2	)	;	
o	.	assertValueCount	(	2	)	;	
o	.	assertTerminated	(	)	;	
}	

@Test	
public	void	testAssertNotMatchCount	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	)	)	;	
TestSubscriber	<	Integer	>	o	=	new	TestSubscriber	<	Integer	>	(	)	;	
oi	.	subscribe	(	o	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	

o	.	assertValue	(	1	)	;	
o	.	assertValueCount	(	2	)	;	
o	.	assertTerminated	(	)	;	
}	

@Test	
public	void	testAssertNotMatchValue	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	)	)	;	
TestSubscriber	<	Integer	>	o	=	new	TestSubscriber	<	Integer	>	(	)	;	
oi	.	subscribe	(	o	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	

o	.	assertValues	(	1	,	3	)	;	
o	.	assertValueCount	(	2	)	;	
o	.	assertTerminated	(	)	;	
}	

@Test	
public	void	assertNeverAtNotMatchingValue	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	)	)	;	
TestSubscriber	<	Integer	>	o	=	new	TestSubscriber	<	Integer	>	(	)	;	
oi	.	subscribe	(	o	)	;	

o	.	assertNever	(	3	)	;	
o	.	assertValueCount	(	2	)	;	
o	.	assertTerminated	(	)	;	
}	

@Test	
public	void	assertNeverAtMatchingValue	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	)	)	;	
TestSubscriber	<	Integer	>	o	=	new	TestSubscriber	<	Integer	>	(	)	;	
oi	.	subscribe	(	o	)	;	

o	.	assertValues	(	1	,	2	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	

o	.	assertNever	(	2	)	;	
o	.	assertValueCount	(	2	)	;	
o	.	assertTerminated	(	)	;	
}	

@Test	
public	void	assertNeverAtMatchingPredicate	(	)	{	
TestSubscriber	<	Integer	>	ts	=	new	TestSubscriber	<	Integer	>	(	)	;	

Flowable	.	just	(	1	,	2	)	.	subscribe	(	ts	)	;	

ts	.	assertValues	(	1	,	2	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	

ts	.	assertNever	(	new	Predicate	<	Integer	>	(	)	{	
@Override	
public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	=	=	1	;	
}	
}	)	;	
}	

@Test	
public	void	assertNeverAtNotMatchingPredicate	(	)	{	
TestSubscriber	<	Integer	>	ts	=	new	TestSubscriber	<	Integer	>	(	)	;	

Flowable	.	just	(	2	,	3	)	.	subscribe	(	ts	)	;	

ts	.	assertNever	(	new	Predicate	<	Integer	>	(	)	{	
@Override	
public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	=	=	1	;	
}	
}	)	;	
}	

@Test	
public	void	testAssertTerminalEventNotReceived	(	)	{	
PublishProcessor	<	Integer	>	p	=	PublishProcessor	.	create	(	)	;	
TestSubscriber	<	Integer	>	o	=	new	TestSubscriber	<	Integer	>	(	)	;	
p	.	subscribe	(	o	)	;	

p	.	onNext	(	1	)	;	
p	.	onNext	(	2	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	

o	.	assertValues	(	1	,	2	)	;	
o	.	assertValueCount	(	2	)	;	
o	.	assertTerminated	(	)	;	
}	

@Test	
public	void	testWrappingMock	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	)	)	;	

Subscriber	<	Integer	>	mockObserver	=	TestHelper	.	mockSubscriber	(	)	;	

oi	.	subscribe	(	new	TestSubscriber	<	Integer	>	(	mockObserver	)	)	;	

InOrder	inOrder	=	inOrder	(	mockObserver	)	;	
inOrder	.	verify	(	mockObserver	,	times	(	1	)	)	.	onNext	(	1	)	;	
inOrder	.	verify	(	mockObserver	,	times	(	1	)	)	.	onNext	(	2	)	;	
inOrder	.	verify	(	mockObserver	,	times	(	1	)	)	.	onComplete	(	)	;	
inOrder	.	verifyNoMoreInteractions	(	)	;	
}	

@Test	
public	void	testWrappingMockWhenUnsubscribeInvolved	(	)	{	
Flowable	<	Integer	>	oi	=	Flowable	.	fromIterable	(	Arrays	.	asList	(	1	,	2	,	3	,	4	,	5	,	6	,	7	,	8	,	9	)	)	.	take	(	2	)	;	
Subscriber	<	Integer	>	mockObserver	=	TestHelper	.	mockSubscriber	(	)	;	
oi	.	subscribe	(	new	TestSubscriber	<	Integer	>	(	mockObserver	)	)	;	

InOrder	inOrder	=	inOrder	(	mockObserver	)	;	
inOrder	.	verify	(	mockObserver	,	times	(	1	)	)	.	onNext	(	1	)	;	
inOrder	.	verify	(	mockObserver	,	times	(	1	)	)	.	onNext	(	2	)	;	
inOrder	.	verify	(	mockObserver	,	times	(	1	)	)	.	onComplete	(	)	;	
inOrder	.	verifyNoMoreInteractions	(	)	;	
}	

@Test	
public	void	testErrorSwallowed	(	)	{	
Flowable	.	error	(	new	RuntimeException	(	)	)	.	subscribe	(	new	TestSubscriber	<	Object	>	(	)	)	;	
}	

@Test	
public	void	testGetEvents	(	)	{	
TestSubscriber	<	Integer	>	to	=	new	TestSubscriber	<	Integer	>	(	)	;	
to	.	onSubscribe	(	EmptySubscription	.	INSTANCE	)	;	
to	.	onNext	(	1	)	;	
to	.	onNext	(	2	)	;	

assertEquals	(	Arrays	.	<	Object	>	asList	(	Arrays	.	asList	(	1	,	2	)	,	
Collections	.	emptyList	(	)	,	
Collections	.	emptyList	(	)	)	,	to	.	getEvents	(	)	)	;	

to	.	onComplete	(	)	;	

assertEquals	(	Arrays	.	<	Object	>	asList	(	Arrays	.	asList	(	1	,	2	)	,	Collections	.	emptyList	(	)	,	
Collections	.	singletonList	(	Notification	.	createOnComplete	(	)	)	)	,	to	.	getEvents	(	)	)	;	

TestException	ex	=	new	TestException	(	)	;	
TestSubscriber	<	Integer	>	to2	=	new	TestSubscriber	<	Integer	>	(	)	;	
to2	.	onSubscribe	(	EmptySubscription	.	INSTANCE	)	;	
to2	.	onNext	(	1	)	;	
to2	.	onNext	(	2	)	;	

assertEquals	(	Arrays	.	<	Object	>	asList	(	Arrays	.	asList	(	1	,	2	)	,	
Collections	.	emptyList	(	)	,	
Collections	.	emptyList	(	)	)	,	to2	.	getEvents	(	)	)	;	

to2	.	onError	(	ex	)	;	

assertEquals	(	Arrays	.	<	Object	>	asList	(	
Arrays	.	asList	(	1	,	2	)	,	
Collections	.	singletonList	(	ex	)	,	
Collections	.	emptyList	(	)	)	,	
to2	.	getEvents	(	)	)	;	
}	

@Test	
public	void	testNullExpected	(	)	{	
TestSubscriber	<	Integer	>	to	=	new	TestSubscriber	<	Integer	>	(	)	;	
to	.	onNext	(	1	)	;	

try	{	
to	.	assertValue	(	(	Integer	)	null	)	;	
}	catch	(	AssertionError	ex	)	{	
return	;	
}	
fail	(	"str"	)	;	
}	

@Test	
public	void	testNullActual	(	)	{	
TestSubscriber	<	Integer	>	to	=	new	TestSubscriber	<	Integer	>	(	)	;	
to	.	onNext	(	null	)	;	

try	{	
to	.	assertValue	(	1	)	;	
}	catch	(	AssertionError	ex	)	{	
return	;	
}	
fail	(	"str"	)	;	
}	

@Test	
public	void	testTerminalErrorOnce	(	)	{	
TestSubscriber	<	Integer	>	to	=	new	TestSubscriber	<	Integer	>	(	)	;	
to	.	onError	(	new	TestException	(	)	)	;	
to	.	onError	(	new	TestException	(	)	)	;	

try	{	
to	.	assertTerminated	(	)	;	
}	catch	(	AssertionError	ex	)	{	
return	;	
}	
fail	(	"str"	)	;	
}	
@Test	
public	void	testTerminalCompletedOnce	(	)	{	
TestSubscriber	<	Integer	>	to	=	new	TestSubscriber	<	Integer	>	(	)	;	
to	.	onComplete	(	)	;	
to	.	onComplete	(	)	;	

try	{	
to	.	assertTerminated	(	)	;	
}	catch	(	AssertionError	ex	)	{	
return	;	
}	
fail	(	"str"	)	;	
}	

@Test	
public	void	testTerminalOneKind	(	)	{	
TestSubscriber	<	Integer	>	to	=	new	TestSubscriber	<	Integer	>	(	)	;	
to	.	onError	(	new	TestException	(	)	)	;	
to	.	onComplete	(	)	;	

try	{	
to	.	assertTerminated	(	)	;	
}	catch	(	AssertionError	ex	)	{	
return	;	
}	
fail	(	"str"	)	;	
}	

@Test	
public	void	createDelegate	(	)	{	
TestObserver	<	Integer	>	ts1	=	TestObserver	.	create	(	)	;	

TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	ts1	)	;	

ts	.	assertNotSubscribed	(	)	;	

assertFalse	(	ts	.	hasSubscription	(	)	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

try	{	
ts	.	assertNotSubscribed	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

assertTrue	(	ts	.	hasSubscription	(	)	)	;	

assertFalse	(	ts	.	isDisposed	(	)	)	;	

ts	.	onNext	(	1	)	;	
ts	.	onError	(	new	TestException	(	)	)	;	
ts	.	onComplete	(	)	;	

ts1	.	assertValue	(	1	)	.	assertError	(	TestException	.	class	)	.	assertComplete	(	)	;	

ts	.	dispose	(	)	;	

assertTrue	(	ts	.	isDisposed	(	)	)	;	

assertTrue	(	ts	.	isTerminated	(	)	)	;	

assertSame	(	Thread	.	currentThread	(	)	,	ts	.	lastThread	(	)	)	;	

try	{	
ts	.	assertNoValues	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertValueCount	(	0	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

ts	.	assertValueSequence	(	Collections	.	singletonList	(	1	)	)	;	

try	{	
ts	.	assertValueSequence	(	Collections	.	singletonList	(	2	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

ts	.	assertValueSet	(	Collections	.	singleton	(	1	)	)	;	

try	{	
ts	.	assertValueSet	(	Collections	.	singleton	(	2	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

}	

@Test	
public	void	assertError	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

try	{	
ts	.	assertError	(	TestException	.	class	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertError	(	new	TestException	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertError	(	Functions	.	<	Throwable	>	alwaysTrue	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertErrorMessage	(	"str"	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertSubscribed	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertTerminated	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	assertSubscribed	(	)	;	

ts	.	assertNoErrors	(	)	;	

TestException	ex	=	new	TestException	(	"str"	)	;	

ts	.	onError	(	ex	)	;	

ts	.	assertError	(	ex	)	;	

ts	.	assertError	(	TestException	.	class	)	;	

ts	.	assertError	(	Functions	.	<	Throwable	>	alwaysTrue	(	)	)	;	

ts	.	assertError	(	new	Predicate	<	Throwable	>	(	)	{	
@Override	
public	boolean	test	(	Throwable	t	)	throws	Exception	{	
return	t	.	getMessage	(	)	!	=	null	&	&	t	.	getMessage	(	)	.	contains	(	"str"	)	;	
}	
}	)	;	

ts	.	assertErrorMessage	(	"str"	)	;	

try	{	
ts	.	assertErrorMessage	(	"str"	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertError	(	new	RuntimeException	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertError	(	IOException	.	class	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertError	(	Functions	.	<	Throwable	>	alwaysFalse	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

try	{	
ts	.	assertNoErrors	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	exc	)	{	
}	

ts	.	assertTerminated	(	)	;	

ts	.	assertValueCount	(	0	)	;	

ts	.	assertNoValues	(	)	;	


}	

@Test	
public	void	emptyObserverEnum	(	)	{	
assertEquals	(	1	,	TestObserver	.	EmptyObserver	.	values	(	)	.	length	)	;	
assertNotNull	(	TestObserver	.	EmptyObserver	.	valueOf	(	"str"	)	)	;	
}	

@Test	
public	void	valueAndClass	(	)	{	
assertEquals	(	"str"	,	TestObserver	.	valueAndClass	(	null	)	)	;	
assertEquals	(	"str"	,	TestObserver	.	valueAndClass	(	1	)	)	;	
}	

@Test	
public	void	assertFailure	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	onError	(	new	TestException	(	"str"	)	)	;	

ts	.	assertFailure	(	TestException	.	class	)	;	

ts	.	assertFailure	(	Functions	.	<	Throwable	>	alwaysTrue	(	)	)	;	

ts	.	assertFailureAndMessage	(	TestException	.	class	,	"str"	)	;	

ts	.	onNext	(	1	)	;	

ts	.	assertFailure	(	TestException	.	class	,	1	)	;	

ts	.	assertFailure	(	Functions	.	<	Throwable	>	alwaysTrue	(	)	,	1	)	;	

ts	.	assertFailureAndMessage	(	TestException	.	class	,	"str"	,	1	)	;	
}	

@Test	
public	void	assertFuseable	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	assertNotFuseable	(	)	;	

try	{	
ts	.	assertFuseable	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertFusionMode	(	QueueDisposable	.	SYNC	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

ts	=	TestObserver	.	create	(	)	;	
ts	.	setInitialFusionMode	(	QueueDisposable	.	ANY	)	;	

ts	.	onSubscribe	(	new	ScalarDisposable	<	Integer	>	(	ts	,	1	)	)	;	

ts	.	assertFuseable	(	)	;	

ts	.	assertFusionMode	(	QueueDisposable	.	SYNC	)	;	

try	{	
ts	.	assertFusionMode	(	QueueDisposable	.	NONE	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertNotFuseable	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

}	

@Test	
public	void	assertTerminated	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	assertNotTerminated	(	)	;	

ts	.	onError	(	null	)	;	

try	{	
ts	.	assertNotTerminated	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	assertOf	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	assertOf	(	new	Consumer	<	TestObserver	<	Integer	>	>	(	)	{	
@Override	
public	void	accept	(	TestObserver	<	Integer	>	f	)	throws	Exception	{	
f	.	assertNotSubscribed	(	)	;	
}	
}	)	;	

try	{	
ts	.	assertOf	(	new	Consumer	<	TestObserver	<	Integer	>	>	(	)	{	
@Override	
public	void	accept	(	TestObserver	<	Integer	>	f	)	throws	Exception	{	
f	.	assertSubscribed	(	)	;	
}	
}	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertOf	(	new	Consumer	<	TestObserver	<	Integer	>	>	(	)	{	
@Override	
public	void	accept	(	TestObserver	<	Integer	>	f	)	throws	Exception	{	
throw	new	IllegalArgumentException	(	)	;	
}	
}	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	IllegalArgumentException	ex	)	{	
}	
}	

@Test	
public	void	assertResult	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	onComplete	(	)	;	

ts	.	assertResult	(	)	;	

try	{	
ts	.	assertResult	(	1	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

ts	.	onNext	(	1	)	;	

ts	.	assertResult	(	1	)	;	

try	{	
ts	.	assertResult	(	2	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertResult	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

}	

@Test	(	timeout	=	5000	)	
public	void	await	(	)	throws	Exception	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

assertFalse	(	ts	.	await	(	100	,	TimeUnit	.	MILLISECONDS	)	)	;	

ts	.	awaitDone	(	100	,	TimeUnit	.	MILLISECONDS	)	;	

assertTrue	(	ts	.	isDisposed	(	)	)	;	

assertFalse	(	ts	.	awaitTerminalEvent	(	100	,	TimeUnit	.	MILLISECONDS	)	)	;	

assertEquals	(	0	,	ts	.	completions	(	)	)	;	
assertEquals	(	0	,	ts	.	errorCount	(	)	)	;	

ts	.	onComplete	(	)	;	

assertTrue	(	ts	.	await	(	100	,	TimeUnit	.	MILLISECONDS	)	)	;	

ts	.	await	(	)	;	

ts	.	awaitDone	(	5	,	TimeUnit	.	SECONDS	)	;	

assertEquals	(	1	,	ts	.	completions	(	)	)	;	
assertEquals	(	0	,	ts	.	errorCount	(	)	)	;	

assertTrue	(	ts	.	awaitTerminalEvent	(	)	)	;	

final	TestObserver	<	Integer	>	ts1	=	TestObserver	.	create	(	)	;	

ts1	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

Schedulers	.	single	(	)	.	scheduleDirect	(	new	Runnable	(	)	{	
@Override	
public	void	run	(	)	{	
ts1	.	onComplete	(	)	;	
}	
}	,	200	,	TimeUnit	.	MILLISECONDS	)	;	

ts1	.	await	(	)	;	

ts1	.	assertValueSet	(	Collections	.	<	Integer	>	emptySet	(	)	)	;	
}	

@Test	
public	void	errors	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

assertEquals	(	0	,	ts	.	errors	(	)	.	size	(	)	)	;	

ts	.	onError	(	new	TestException	(	)	)	;	

assertEquals	(	1	,	ts	.	errors	(	)	.	size	(	)	)	;	

TestHelper	.	assertError	(	ts	.	errors	(	)	,	0	,	TestException	.	class	)	;	
}	

@SuppressWarnings	(	"str"	)	
@Test	
public	void	onNext	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

assertEquals	(	0	,	ts	.	valueCount	(	)	)	;	

assertEquals	(	Collections	.	emptyList	(	)	,	ts	.	values	(	)	)	;	

ts	.	onNext	(	1	)	;	

assertEquals	(	Collections	.	singletonList	(	1	)	,	ts	.	values	(	)	)	;	

ts	.	cancel	(	)	;	

assertTrue	(	ts	.	isCancelled	(	)	)	;	
assertTrue	(	ts	.	isDisposed	(	)	)	;	

ts	.	assertValue	(	1	)	;	

assertEquals	(	Arrays	.	asList	(	Collections	.	singletonList	(	1	)	,	Collections	.	emptyList	(	)	,	Collections	.	emptyList	(	)	)	,	ts	.	getEvents	(	)	)	;	

ts	.	onComplete	(	)	;	

assertEquals	(	Arrays	.	asList	(	Collections	.	singletonList	(	1	)	,	Collections	.	emptyList	(	)	,	Collections	.	singletonList	(	Notification	.	createOnComplete	(	)	)	)	,	ts	.	getEvents	(	)	)	;	
}	

@Test	
public	void	fusionModeToString	(	)	{	
assertEquals	(	"str"	,	TestObserver	.	fusionModeToString	(	QueueDisposable	.	NONE	)	)	;	
assertEquals	(	"str"	,	TestObserver	.	fusionModeToString	(	QueueDisposable	.	SYNC	)	)	;	
assertEquals	(	"str"	,	TestObserver	.	fusionModeToString	(	QueueDisposable	.	ASYNC	)	)	;	
assertEquals	(	"str"	,	TestObserver	.	fusionModeToString	(	100	)	)	;	
}	

@Test	
public	void	multipleTerminals	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	assertNotComplete	(	)	;	

ts	.	onComplete	(	)	;	

try	{	
ts	.	assertNotComplete	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	

ts	.	assertTerminated	(	)	;	

ts	.	onComplete	(	)	;	

try	{	
ts	.	assertComplete	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	

try	{	
ts	.	assertTerminated	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	

try	{	
ts	.	assertNotComplete	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	
}	

@Test	
public	void	assertValue	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

try	{	
ts	.	assertValue	(	1	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	

ts	.	onNext	(	1	)	;	

ts	.	assertValue	(	1	)	;	

try	{	
ts	.	assertValue	(	2	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	

ts	.	onNext	(	2	)	;	

try	{	
ts	.	assertValue	(	1	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	Throwable	ex	)	{	
}	
}	

@Test	
public	void	onNextMisbehave	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onNext	(	1	)	;	

ts	.	assertError	(	IllegalStateException	.	class	)	;	

ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	onNext	(	null	)	;	

ts	.	assertFailure	(	NullPointerException	.	class	,	(	Integer	)	null	)	;	
}	

@Test	
public	void	awaitTerminalEventInterrupt	(	)	{	
final	TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

Thread	.	currentThread	(	)	.	interrupt	(	)	;	

ts	.	awaitTerminalEvent	(	)	;	

assertTrue	(	Thread	.	interrupted	(	)	)	;	

Thread	.	currentThread	(	)	.	interrupt	(	)	;	

ts	.	awaitTerminalEvent	(	5	,	TimeUnit	.	SECONDS	)	;	

assertTrue	(	Thread	.	interrupted	(	)	)	;	
}	

@Test	
public	void	assertTerminated2	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

assertFalse	(	ts	.	isTerminated	(	)	)	;	

ts	.	onError	(	new	TestException	(	)	)	;	
ts	.	onError	(	new	IOException	(	)	)	;	

assertTrue	(	ts	.	isTerminated	(	)	)	;	

try	{	
ts	.	assertTerminated	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

try	{	
ts	.	assertError	(	TestException	.	class	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	


ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	onError	(	new	TestException	(	)	)	;	
ts	.	onComplete	(	)	;	

try	{	
ts	.	assertTerminated	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	onSubscribe	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	null	)	;	

ts	.	assertError	(	NullPointerException	.	class	)	;	

ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

Disposable	d1	=	Disposables	.	empty	(	)	;	

ts	.	onSubscribe	(	d1	)	;	

assertTrue	(	d1	.	isDisposed	(	)	)	;	

ts	.	assertError	(	IllegalStateException	.	class	)	;	

ts	=	TestObserver	.	create	(	)	;	
ts	.	dispose	(	)	;	

d1	=	Disposables	.	empty	(	)	;	

ts	.	onSubscribe	(	d1	)	;	

assertTrue	(	d1	.	isDisposed	(	)	)	;	

}	

@Test	
public	void	assertValueSequence	(	)	{	
TestObserver	<	Integer	>	ts	=	TestObserver	.	create	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	onNext	(	1	)	;	
ts	.	onNext	(	2	)	;	

try	{	
ts	.	assertValueSequence	(	Collections	.	<	Integer	>	emptyList	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	expected	)	{	
assertTrue	(	expected	.	getMessage	(	)	,	expected	.	getMessage	(	)	.	startsWith	(	"str"	)	)	;	
}	

try	{	
ts	.	assertValueSequence	(	Collections	.	singletonList	(	1	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	expected	)	{	
assertTrue	(	expected	.	getMessage	(	)	,	expected	.	getMessage	(	)	.	startsWith	(	"str"	)	)	;	
}	

ts	.	assertValueSequence	(	Arrays	.	asList	(	1	,	2	)	)	;	

try	{	
ts	.	assertValueSequence	(	Arrays	.	asList	(	1	,	2	,	3	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	expected	)	{	
assertTrue	(	expected	.	getMessage	(	)	,	expected	.	getMessage	(	)	.	startsWith	(	"str"	)	)	;	
}	
}	

@Test	
public	void	assertEmpty	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

try	{	
ts	.	assertEmpty	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

ts	.	assertEmpty	(	)	;	

ts	.	onNext	(	1	)	;	

try	{	
ts	.	assertEmpty	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	awaitDoneTimed	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Thread	.	currentThread	(	)	.	interrupt	(	)	;	

try	{	
ts	.	awaitDone	(	5	,	TimeUnit	.	SECONDS	)	;	
}	catch	(	RuntimeException	ex	)	{	
assertTrue	(	ex	.	toString	(	)	,	ex	.	getCause	(	)	instanceof	InterruptedException	)	;	
}	
}	

@Test	
public	void	assertNotSubscribed	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

ts	.	assertNotSubscribed	(	)	;	

ts	.	errors	(	)	.	add	(	new	TestException	(	)	)	;	

try	{	
ts	.	assertNotSubscribed	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	assertErrorMultiple	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

TestException	e	=	new	TestException	(	)	;	
ts	.	errors	(	)	.	add	(	e	)	;	
ts	.	errors	(	)	.	add	(	new	TestException	(	)	)	;	

try	{	
ts	.	assertError	(	TestException	.	class	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
try	{	
ts	.	assertError	(	e	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
try	{	
ts	.	assertError	(	Functions	.	<	Throwable	>	alwaysTrue	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
try	{	
ts	.	assertErrorMessage	(	"str"	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	testErrorInPredicate	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	
ts	.	onError	(	new	RuntimeException	(	)	)	;	
try	{	
ts	.	assertError	(	new	Predicate	<	Throwable	>	(	)	{	
@Override	
public	boolean	test	(	Throwable	throwable	)	throws	Exception	{	
throw	new	TestException	(	)	;	
}	
}	)	;	
}	catch	(	TestException	ex	)	{	
return	;	
}	
fail	(	"str"	)	;	
}	

@Test	
public	void	assertComplete	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

try	{	
ts	.	assertComplete	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	

ts	.	onComplete	(	)	;	

ts	.	assertComplete	(	)	;	

ts	.	onComplete	(	)	;	

try	{	
ts	.	assertComplete	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	completeWithoutOnSubscribe	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

ts	.	onComplete	(	)	;	

ts	.	assertError	(	IllegalStateException	.	class	)	;	
}	

@Test	
public	void	completeDelegateThrows	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	new	Observer	<	Integer	>	(	)	{	

@Override	
public	void	onSubscribe	(	Disposable	d	)	{	

}	

@Override	
public	void	onNext	(	Integer	value	)	{	

}	

@Override	
public	void	onError	(	Throwable	e	)	{	
throw	new	TestException	(	)	;	
}	

@Override	
public	void	onComplete	(	)	{	
throw	new	TestException	(	)	;	
}	

}	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

try	{	
ts	.	onComplete	(	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	TestException	ex	)	{	
assertTrue	(	ts	.	isTerminated	(	)	)	;	
}	
}	

@Test	
public	void	errorDelegateThrows	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	new	Observer	<	Integer	>	(	)	{	

@Override	
public	void	onSubscribe	(	Disposable	d	)	{	

}	

@Override	
public	void	onNext	(	Integer	value	)	{	

}	

@Override	
public	void	onError	(	Throwable	e	)	{	
throw	new	TestException	(	)	;	
}	

@Override	
public	void	onComplete	(	)	{	
throw	new	TestException	(	)	;	
}	

}	)	;	

ts	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

try	{	
ts	.	onError	(	new	IOException	(	)	)	;	
throw	new	RuntimeException	(	"str"	)	;	
}	catch	(	TestException	ex	)	{	
assertTrue	(	ts	.	isTerminated	(	)	)	;	
}	
}	

@Test	
public	void	syncQueueThrows	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	
ts	.	setInitialFusionMode	(	QueueDisposable	.	SYNC	)	;	

Observable	.	range	(	1	,	5	)	
.	map	(	new	Function	<	Integer	,	Object	>	(	)	{	
@Override	
public	Object	apply	(	Integer	v	)	throws	Exception	{	throw	new	TestException	(	)	;	}	
}	)	
.	subscribe	(	ts	)	;	

ts	.	assertSubscribed	(	)	
.	assertFuseable	(	)	
.	assertFusionMode	(	QueueDisposable	.	SYNC	)	
.	assertFailure	(	TestException	.	class	)	;	
}	

@Test	
public	void	asyncQueueThrows	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	
ts	.	setInitialFusionMode	(	QueueDisposable	.	ANY	)	;	

UnicastSubject	<	Integer	>	up	=	UnicastSubject	.	create	(	)	;	

up	
.	map	(	new	Function	<	Integer	,	Object	>	(	)	{	
@Override	
public	Object	apply	(	Integer	v	)	throws	Exception	{	throw	new	TestException	(	)	;	}	
}	)	
.	subscribe	(	ts	)	;	

up	.	onNext	(	1	)	;	

ts	.	assertSubscribed	(	)	
.	assertFuseable	(	)	
.	assertFusionMode	(	QueueDisposable	.	ASYNC	)	
.	assertFailure	(	TestException	.	class	)	;	
}	

@Test	
public	void	completedMeansDisposed	(	)	{	
assertFalse	(	Observable	.	just	(	1	)	
.	test	(	)	
.	assertResult	(	1	)	.	isDisposed	(	)	)	;	
}	

@Test	
public	void	errorMeansDisposed	(	)	{	
assertFalse	(	Observable	.	error	(	new	TestException	(	)	)	
.	test	(	)	
.	assertFailure	(	TestException	.	class	)	.	isDisposed	(	)	)	;	
}	

@Test	
public	void	asyncFusion	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	
ts	.	setInitialFusionMode	(	QueueDisposable	.	ANY	)	;	

UnicastSubject	<	Integer	>	up	=	UnicastSubject	.	create	(	)	;	

up	
.	subscribe	(	ts	)	;	

up	.	onNext	(	1	)	;	
up	.	onComplete	(	)	;	

ts	.	assertSubscribed	(	)	
.	assertFuseable	(	)	
.	assertFusionMode	(	QueueDisposable	.	ASYNC	)	
.	assertResult	(	1	)	;	
}	

@Test	
public	void	assertValuePredicateEmpty	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	

Observable	.	empty	(	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValue	(	new	Predicate	<	Object	>	(	)	{	
@Override	public	boolean	test	(	final	Object	o	)	throws	Exception	{	
return	false	;	
}	
}	)	;	
}	

@Test	
public	void	assertValuePredicateMatch	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Observable	.	just	(	1	)	.	subscribe	(	ts	)	;	

ts	.	assertValue	(	new	Predicate	<	Integer	>	(	)	{	
@Override	public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	=	=	1	;	
}	
}	)	;	
}	

@Test	
public	void	assertValuePredicateNoMatch	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Observable	.	just	(	1	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValue	(	new	Predicate	<	Integer	>	(	)	{	
@Override	public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	!	=	1	;	
}	
}	)	;	
}	

@Test	
public	void	assertValuePredicateMatchButMore	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Observable	.	just	(	1	,	2	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValue	(	new	Predicate	<	Integer	>	(	)	{	
@Override	public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	=	=	1	;	
}	
}	)	;	
}	

@Test	
public	void	assertValueAtPredicateEmpty	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	

Observable	.	empty	(	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValueAt	(	0	,	new	Predicate	<	Object	>	(	)	{	
@Override	public	boolean	test	(	final	Object	o	)	throws	Exception	{	
return	false	;	
}	
}	)	;	
}	

@Test	
public	void	assertValueAtPredicateMatch	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Observable	.	just	(	1	,	2	)	.	subscribe	(	ts	)	;	

ts	.	assertValueAt	(	1	,	new	Predicate	<	Integer	>	(	)	{	
@Override	public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	=	=	2	;	
}	
}	)	;	
}	

@Test	
public	void	assertValueAtPredicateNoMatch	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Observable	.	just	(	1	,	2	,	3	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValueAt	(	2	,	new	Predicate	<	Integer	>	(	)	{	
@Override	public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	!	=	3	;	
}	
}	)	;	
}	

@Test	
public	void	assertValueAtInvalidIndex	(	)	{	
TestObserver	<	Integer	>	ts	=	new	TestObserver	<	Integer	>	(	)	;	

Observable	.	just	(	1	,	2	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValueAt	(	2	,	new	Predicate	<	Integer	>	(	)	{	
@Override	public	boolean	test	(	final	Integer	o	)	throws	Exception	{	
return	o	=	=	1	;	
}	
}	)	;	
}	

@Test	
public	void	assertValueAtIndexEmpty	(	)	{	
TestObserver	<	Object	>	ts	=	new	TestObserver	<	Object	>	(	)	;	

Observable	.	empty	(	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValueAt	(	0	,	"str"	)	;	
}	

@Test	
public	void	assertValueAtIndexMatch	(	)	{	
TestObserver	<	String	>	ts	=	new	TestObserver	<	String	>	(	)	;	

Observable	.	just	(	"str"	,	"str"	)	.	subscribe	(	ts	)	;	

ts	.	assertValueAt	(	1	,	"str"	)	;	
}	

@Test	
public	void	assertValueAtIndexNoMatch	(	)	{	
TestObserver	<	String	>	ts	=	new	TestObserver	<	String	>	(	)	;	

Observable	.	just	(	"str"	,	"str"	,	"str"	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValueAt	(	2	,	"str"	)	;	
}	

@Test	
public	void	assertValueAtIndexInvalidIndex	(	)	{	
TestObserver	<	String	>	ts	=	new	TestObserver	<	String	>	(	)	;	

Observable	.	just	(	"str"	,	"str"	)	.	subscribe	(	ts	)	;	

thrown	.	expect	(	AssertionError	.	class	)	;	
thrown	.	expectMessage	(	"str"	)	;	
ts	.	assertValueAt	(	2	,	"str"	)	;	
}	

@Test	
public	void	withTag	(	)	{	
try	{	
for	(	int	i	=	1	;	i	<	3	;	i	+	+	)	{	
Observable	.	just	(	i	)	
.	test	(	)	
.	withTag	(	"str"	+	i	)	
.	assertResult	(	1	)	
;	
}	
fail	(	"str"	)	;	
}	catch	(	AssertionError	ex	)	{	
assertTrue	(	ex	.	toString	(	)	,	ex	.	toString	(	)	.	contains	(	"str"	)	)	;	
}	
}	

@Test	
public	void	assertValuesOnly	(	)	{	
TestObserver	<	Integer	>	to	=	TestObserver	.	create	(	)	;	
to	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	
to	.	assertValuesOnly	(	)	;	

to	.	onNext	(	5	)	;	
to	.	assertValuesOnly	(	5	)	;	

to	.	onNext	(	-	1	)	;	
to	.	assertValuesOnly	(	5	,	-	1	)	;	
}	

@Test	
public	void	assertValuesOnlyThrowsOnUnexpectedValue	(	)	{	
TestObserver	<	Integer	>	to	=	TestObserver	.	create	(	)	;	
to	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	
to	.	assertValuesOnly	(	)	;	

to	.	onNext	(	5	)	;	
to	.	assertValuesOnly	(	5	)	;	

to	.	onNext	(	-	1	)	;	

try	{	
to	.	assertValuesOnly	(	5	)	;	
fail	(	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	assertValuesOnlyThrowsWhenCompleted	(	)	{	
TestObserver	<	Integer	>	to	=	TestObserver	.	create	(	)	;	
to	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

to	.	onComplete	(	)	;	

try	{	
to	.	assertValuesOnly	(	)	;	
fail	(	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	

@Test	
public	void	assertValuesOnlyThrowsWhenErrored	(	)	{	
TestObserver	<	Integer	>	to	=	TestObserver	.	create	(	)	;	
to	.	onSubscribe	(	Disposables	.	empty	(	)	)	;	

to	.	onError	(	new	TestException	(	)	)	;	

try	{	
to	.	assertValuesOnly	(	)	;	
fail	(	)	;	
}	catch	(	AssertionError	ex	)	{	
}	
}	
}	
