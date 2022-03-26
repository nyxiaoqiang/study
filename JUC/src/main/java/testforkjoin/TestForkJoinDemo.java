package testforkjoin;


import java.util.concurrent.RecursiveTask;

public class TestForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    private Long temp = 5000L;

    public TestForkJoinDemo(Long start,Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start < temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum = sum + i;
            }
            return sum;
        }else {
            Long middle = (start + end)/2;
            TestForkJoinDemo testForkJoin1 = new TestForkJoinDemo(start,middle);
            testForkJoin1.fork();
            TestForkJoinDemo testForkJoin2 = new TestForkJoinDemo(middle+1,end);
            testForkJoin2.fork();
            return testForkJoin1.join() + testForkJoin2.join();
        }
    }
}
