package pe.cibertec.cleanarchitecture.domain.usecase;

import pe.cibertec.cleanarchitecture.domain.executor.PostExecutionThread;
import pe.cibertec.cleanarchitecture.domain.executor.ThreadExecutor;
import pe.cibertec.cleanarchitecture.domain.model.News;
import pe.cibertec.cleanarchitecture.domain.repository.NewsRepository;
import pe.cibertec.cleanarchitecture.domain.repository.RepositoryCallback;

/**
 * Created by USUARIO on 1/06/2017.
 */

public class InsertNews extends UseCase<News>{

    final NewsRepository newsRepository;

    public InsertNews(NewsRepository newsRepository,
                      ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        super(threadExecutor,postExecutionThread);
        this.newsRepository = newsRepository;
    }


    @Override
    protected void buildUseCase() {
   }

    @Override
    protected void buildUseCaseParameters(News news) {
        this.newsRepository.insert(news ,new RepositoryCallback<News>() {
            @Override
            public void onSuccess(News news) {
                notifyUseCaseSuccess(news);
            }

            @Override
            public void onError(Throwable exception) {
                notifyUseCaseError(exception);
            }
        });
    }


}
