package pe.cibertec.cleanarchitecture.domain.usecase;

import pe.cibertec.cleanarchitecture.domain.executor.PostExecutionThread;
import pe.cibertec.cleanarchitecture.domain.executor.ThreadExecutor;
import pe.cibertec.cleanarchitecture.domain.model.News;

/**
 * Created by Android on 27/05/2017.
 */

public abstract class UseCase<T> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private Callback<T> callback;

    public UseCase(ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract void buildUseCase();

    protected abstract void buildUseCaseParameters(News news);

    public void execute(Callback<T> callback) {
        this.callback = callback;
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                buildUseCase();
            }
        });
    }

    public void executeWithParameters(final News news, Callback<T> callback) {
        this.callback = callback;
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                buildUseCaseParameters(news);
            }
        });
    }

    public void cancel() {
        this.callback = null;
    }

    protected void notifyUseCaseSuccess(final T response) {
        this.postExecutionThread.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(response);
                }
            }
        });
    }

    protected void notifyUseCaseError(final Throwable e) {
        this.postExecutionThread.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }

    public interface Callback<T> {

        void onSuccess(T t);

        void onError(Throwable exception);
    }
}
