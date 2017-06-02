package pe.cibertec.cleanarchitecture.presentation.model.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.cibertec.cleanarchitecture.domain.model.News;
import pe.cibertec.cleanarchitecture.presentation.model.NewsModel;

/**
 * Created by Android on 27/05/2017.
 */

public class NewsModelDataMapper {

    public NewsModelDataMapper() {
    }

    public NewsModel transform(News news) {
        NewsModel newsModel = new NewsModel(news.getObjectId());
        newsModel.setTitle(news.getTitle());
        return newsModel;
    }

    public List<NewsModel> transform(List<News> newsList) {
        List<NewsModel> newsModelList = new ArrayList<>();
        for(News news : newsList) {
            newsModelList.add(transform(news));
        }
        return newsModelList;
    }

    public News sendItTransform(NewsModel newsModel) {
        News news = new News(newsModel.getObjectId());
        news.setTitle(newsModel.getTitle());
        return news;
    }

    public List<News> sendItTransform(List<NewsModel> newsModelList) {

        List<News> newsList = new ArrayList<>();
        for(NewsModel  newsModel : newsModelList) {
            newsList.add(sendItTransform(newsModel));
        }
        return newsList;
    }
}
