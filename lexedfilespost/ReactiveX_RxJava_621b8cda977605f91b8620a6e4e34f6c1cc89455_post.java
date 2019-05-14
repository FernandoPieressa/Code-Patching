














package	io	.	reactivex	.	exceptions	;	

import static	org	.	junit	.	Assert	.	*	;	

import	java	.	io	.	*	;	
import	java	.	util	.	*	;	

import	org	.	junit	.	Test	;	

import	io	.	reactivex	.	exceptions	.	CompositeException	.	CompositeExceptionCausalChain	;	

public	class	CompositeExceptionTest	{	

private	final	Throwable	ex1	=	new	Throwable	(	"str"	)	;	
private	final	Throwable	ex2	=	new	Throwable	(	"str"	,	ex1	)	;	
private	final	Throwable	ex3	=	new	Throwable	(	"str"	,	ex2	)	;	

private	CompositeException	getNewCompositeExceptionWithEx123	(	)	{	
List	<	Throwable	>	throwables	=	new	ArrayList	<	Throwable	>	(	)	;	
throwables	.	add	(	ex1	)	;	
throwables	.	add	(	ex2	)	;	
throwables	.	add	(	ex3	)	;	
return	new	CompositeException	(	throwables	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testMultipleWithSameCause	(	)	{	
Throwable	rootCause	=	new	Throwable	(	"str"	)	;	
Throwable	e1	=	new	Throwable	(	"str"	,	rootCause	)	;	
Throwable	e2	=	new	Throwable	(	"str"	,	rootCause	)	;	
Throwable	e3	=	new	Throwable	(	"str"	,	rootCause	)	;	
CompositeException	ce	=	new	CompositeException	(	e1	,	e2	,	e3	)	;	

System	.	err	.	println	(	"str"	)	;	
ce	.	printStackTrace	(	)	;	
assertEquals	(	3	,	ce	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	ce	)	;	
assertNotNull	(	getRootCause	(	ce	)	)	;	
System	.	err	.	println	(	"str"	)	;	
ce	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	
public	void	testEmptyErrors	(	)	{	
try	{	
new	CompositeException	(	)	;	
fail	(	"str"	)	;	
}	catch	(	IllegalArgumentException	e	)	{	
assertEquals	(	"str"	,	e	.	getMessage	(	)	)	;	
}	
try	{	
new	CompositeException	(	new	ArrayList	<	Throwable	>	(	)	)	;	
fail	(	"str"	)	;	
}	catch	(	IllegalArgumentException	e	)	{	
assertEquals	(	"str"	,	e	.	getMessage	(	)	)	;	
}	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionFromParentThenChild	(	)	{	
CompositeException	cex	=	new	CompositeException	(	ex1	,	ex2	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	2	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionFromChildThenParent	(	)	{	
CompositeException	cex	=	new	CompositeException	(	ex2	,	ex1	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	2	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionFromChildAndComposite	(	)	{	
CompositeException	cex	=	new	CompositeException	(	ex1	,	getNewCompositeExceptionWithEx123	(	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	3	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionFromCompositeAndChild	(	)	{	
CompositeException	cex	=	new	CompositeException	(	getNewCompositeExceptionWithEx123	(	)	,	ex1	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	3	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionFromTwoDuplicateComposites	(	)	{	
List	<	Throwable	>	exs	=	new	ArrayList	<	Throwable	>	(	)	;	
exs	.	add	(	getNewCompositeExceptionWithEx123	(	)	)	;	
exs	.	add	(	getNewCompositeExceptionWithEx123	(	)	)	;	
CompositeException	cex	=	new	CompositeException	(	exs	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	3	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	





private	static	void	assertNoCircularReferences	(	Throwable	ex	)	{	
ByteArrayOutputStream	baos	=	new	ByteArrayOutputStream	(	)	;	
PrintStream	printStream	=	new	PrintStream	(	baos	)	;	
ex	.	printStackTrace	(	printStream	)	;	
assertFalse	(	baos	.	toString	(	)	.	contains	(	"str"	)	)	;	
}	

private	static	Throwable	getRootCause	(	Throwable	ex	)	{	
Throwable	root	=	ex	.	getCause	(	)	;	
if	(	root	=	=	null	)	{	
return	null	;	
}	else	{	
while	(	true	)	{	
if	(	root	.	getCause	(	)	=	=	null	)	{	
return	root	;	
}	else	{	
root	=	root	.	getCause	(	)	;	
}	
}	
}	
}	

@Test	
public	void	testNullCollection	(	)	{	
CompositeException	composite	=	new	CompositeException	(	(	List	<	Throwable	>	)	null	)	;	
composite	.	getCause	(	)	;	
composite	.	printStackTrace	(	)	;	
}	

@Test	
public	void	testNullElement	(	)	{	
CompositeException	composite	=	new	CompositeException	(	Collections	.	singletonList	(	(	Throwable	)	null	)	)	;	
composite	.	getCause	(	)	;	
composite	.	printStackTrace	(	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionWithUnsupportedInitCause	(	)	{	
Throwable	t	=	new	Throwable	(	)	{	

private	static	final	long	serialVersionUID	=	-	3282577447436848385L	;	

@Override	
public	synchronized	Throwable	initCause	(	Throwable	cause	)	{	
throw	new	UnsupportedOperationException	(	)	;	
}	
}	;	
CompositeException	cex	=	new	CompositeException	(	t	,	ex1	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	2	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	(	timeout	=	1000	)	
public	void	testCompositeExceptionWithNullInitCause	(	)	{	
Throwable	t	=	new	Throwable	(	"str"	)	{	

private	static	final	long	serialVersionUID	=	-	7984762607894527888L	;	

@Override	
public	synchronized	Throwable	initCause	(	Throwable	cause	)	{	
return	null	;	
}	
}	;	
CompositeException	cex	=	new	CompositeException	(	t	,	ex1	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	printStackTrace	(	)	;	
assertEquals	(	2	,	cex	.	getExceptions	(	)	.	size	(	)	)	;	

assertNoCircularReferences	(	cex	)	;	
assertNotNull	(	getRootCause	(	cex	)	)	;	

System	.	err	.	println	(	"str"	)	;	
cex	.	getCause	(	)	.	printStackTrace	(	)	;	
}	

@Test	
public	void	messageCollection	(	)	{	
CompositeException	compositeException	=	new	CompositeException	(	ex1	,	ex3	)	;	
assertEquals	(	"str"	,	compositeException	.	getMessage	(	)	)	;	
}	

@Test	
public	void	messageVarargs	(	)	{	
CompositeException	compositeException	=	new	CompositeException	(	ex1	,	ex2	,	ex3	)	;	
assertEquals	(	"str"	,	compositeException	.	getMessage	(	)	)	;	
}	

@Test	
public	void	complexCauses	(	)	{	
Throwable	e1	=	new	Throwable	(	"str"	)	;	
Throwable	e2	=	new	Throwable	(	"str"	)	;	
e1	.	initCause	(	e2	)	;	

Throwable	e3	=	new	Throwable	(	"str"	)	;	
Throwable	e4	=	new	Throwable	(	"str"	)	;	
e3	.	initCause	(	e4	)	;	

Throwable	e5	=	new	Throwable	(	"str"	)	;	
Throwable	e6	=	new	Throwable	(	"str"	)	;	
e5	.	initCause	(	e6	)	;	

CompositeException	compositeException	=	new	CompositeException	(	e1	,	e3	,	e5	)	;	
assertTrue	(	compositeException	.	getCause	(	)	instanceof	CompositeExceptionCausalChain	)	;	

List	<	Throwable	>	causeChain	=	new	ArrayList	<	Throwable	>	(	)	;	
Throwable	cause	=	compositeException	.	getCause	(	)	.	getCause	(	)	;	
while	(	cause	!	=	null	)	{	
causeChain	.	add	(	cause	)	;	
cause	=	cause	.	getCause	(	)	;	
}	
assertEquals	(	Arrays	.	asList	(	e1	,	e2	,	e3	,	e4	,	e5	,	e6	)	,	causeChain	)	;	
}	

@Test	
public	void	constructorWithNull	(	)	{	
assertTrue	(	new	CompositeException	(	(	Throwable	[	]	)	null	)	.	getExceptions	(	)	.	get	(	0	)	instanceof	NullPointerException	)	;	

assertTrue	(	new	CompositeException	(	(	Iterable	<	Throwable	>	)	null	)	.	getExceptions	(	)	.	get	(	0	)	instanceof	NullPointerException	)	;	

assertTrue	(	new	CompositeException	(	null	,	new	TestException	(	)	)	.	getExceptions	(	)	.	get	(	0	)	instanceof	NullPointerException	)	;	
}	

@Test	
public	void	printStackTrace	(	)	{	
StringWriter	sw	=	new	StringWriter	(	)	;	
PrintWriter	pw	=	new	PrintWriter	(	sw	)	;	

new	CompositeException	(	new	TestException	(	)	)	.	printStackTrace	(	pw	)	;	

assertTrue	(	sw	.	toString	(	)	.	contains	(	"str"	)	)	;	
}	

@Test	
public	void	cyclicRootCause	(	)	{	
RuntimeException	te	=	new	RuntimeException	(	)	{	

private	static	final	long	serialVersionUID	=	-	8492568224555229753L	;	
Throwable	cause	;	

@Override	
public	Throwable	initCause	(	Throwable	c	)	{	
return	this	;	
}	

@Override	
public	Throwable	getCause	(	)	{	
return	cause	;	
}	
}	;	

assertSame	(	te	,	new	CompositeException	(	te	)	.	getCause	(	)	.	getCause	(	)	)	;	

assertSame	(	te	,	new	CompositeException	(	new	RuntimeException	(	te	)	)	.	getCause	(	)	.	getCause	(	)	.	getCause	(	)	)	;	
}	

@Test	
public	void	nullRootCause	(	)	{	
RuntimeException	te	=	new	RuntimeException	(	)	{	

private	static	final	long	serialVersionUID	=	-	8492568224555229753L	;	

@Override	
public	Throwable	getCause	(	)	{	
return	null	;	
}	
}	;	

assertSame	(	te	,	new	CompositeException	(	te	)	.	getCause	(	)	.	getCause	(	)	)	;	

assertSame	(	te	,	new	CompositeException	(	new	RuntimeException	(	te	)	)	.	getCause	(	)	.	getCause	(	)	.	getCause	(	)	)	;	
}	

@Test	
public	void	badException	(	)	{	
Throwable	e	=	new	BadException	(	)	;	
assertSame	(	e	,	new	CompositeException	(	e	)	.	getCause	(	)	.	getCause	(	)	)	;	
assertSame	(	e	,	new	CompositeException	(	new	RuntimeException	(	e	)	)	.	getCause	(	)	.	getCause	(	)	.	getCause	(	)	)	;	
}	

@Test	
public	void	rootCauseEval	(	)	{	
final	TestException	ex0	=	new	TestException	(	)	;	
Throwable	throwable	=	new	Throwable	(	)	{	

private	static	final	long	serialVersionUID	=	3597694032723032281L	;	

@Override	
public	synchronized	Throwable	getCause	(	)	{	
return	ex0	;	
}	
}	;	
CompositeException	ex	=	new	CompositeException	(	throwable	)	;	
assertSame	(	ex0	,	ex	.	getRootCause	(	ex	)	)	;	
}	

@Test	
public	void	rootCauseSelf	(	)	{	
Throwable	throwable	=	new	Throwable	(	)	{	

private	static	final	long	serialVersionUID	=	-	4398003222998914415L	;	

@Override	
public	synchronized	Throwable	getCause	(	)	{	
return	this	;	
}	
}	;	
CompositeException	tmp	=	new	CompositeException	(	new	TestException	(	)	)	;	
assertSame	(	throwable	,	tmp	.	getRootCause	(	throwable	)	)	;	
}	
}	

final	class	BadException	extends	Throwable	{	
private	static	final	long	serialVersionUID	=	8999507293896399171L	;	

@Override	
public	synchronized	Throwable	getCause	(	)	{	
return	this	;	
}	
}	
