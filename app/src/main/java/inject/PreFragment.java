package inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rowen on 2018/6/9.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreFragment {

}
