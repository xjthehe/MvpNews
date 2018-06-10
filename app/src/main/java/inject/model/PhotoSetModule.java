package inject.model;

import dagger.Module;
import model.news.photoset.PhotoSetActivity;

/**
 * Created by lenovo on 2018/6/10.
 */
@Module
public class PhotoSetModule {
    private final PhotoSetActivity mView;
    private final String  mPhotoSetId;

    public PhotoSetModule(PhotoSetActivity mView, String mPhotoSetId) {
        this.mView = mView;
        this.mPhotoSetId = mPhotoSetId;
    }




}
