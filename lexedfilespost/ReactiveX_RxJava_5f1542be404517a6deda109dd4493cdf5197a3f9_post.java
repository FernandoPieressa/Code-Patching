












package	io	.	reactivex	.	internal	.	schedulers	;	

import	io	.	reactivex	.	Scheduler	;	
import	io	.	reactivex	.	annotations	.	*	;	










@Experimental	
public	interface	SchedulerMultiWorkerSupport	{	








void	createWorkers	(	int	number	,	@NonNull	WorkerCallback	callback	)	;	





interface	WorkerCallback	{	





void	onWorker	(	int	index	,	@NonNull	Scheduler	.	Worker	worker	)	;	
}	
}	
