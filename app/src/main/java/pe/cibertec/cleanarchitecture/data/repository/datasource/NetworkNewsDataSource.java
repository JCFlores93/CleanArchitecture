package pe.cibertec.cleanarchitecture.data.repository.datasource;

import java.util.List;

import pe.cibertec.cleanarchitecture.data.cache.NewsCache;
import pe.cibertec.cleanarchitecture.data.entity.NewsEntity;
import pe.cibertec.cleanarchitecture.data.net.RestApi;
import pe.cibertec.cleanarchitecture.domain.model.News;

/**
 * Created by Android on 27/05/2017.
 */

public class NetworkNewsDataSource implements NewsDataSource

{

    private final RestApi restApi;
    private final NewsCache newsCache;

    public NetworkNewsDataSource(RestApi restApi, NewsCache newsCache) {
        this.restApi = restApi;
        this.newsCache = newsCache;
    }

    @Override
    public void newsEntityList(final DataSourceCallback<List<NewsEntity>> callback) {
        this.restApi.getNewsList(new RestApi.Callback<List<NewsEntity>>() {
            @Override
            public void onSuccess(List<NewsEntity> response) {
                newsCache.put(response);
                callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }

    @Override
    public void newsEntityNews(NewsEntity newsEntity, final DataSourceCallback<NewsEntity> callback) {
        this.restApi.insertUsers(newsEntity,new RestApi.Callback<NewsEntity>() {

            @Override
            public void onSuccess(NewsEntity response) {
                  //  newsCache.put(response);
                    callback.onSuccess(response);
                }


            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }


}
