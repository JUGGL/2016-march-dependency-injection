package us.juggl.twentysixteen.march.ioc;

import dagger.Component;
import us.juggl.twentysixteen.march.dao.CustomerDAO;

import javax.inject.Singleton;

/**
 * Created by dphillips on 3/12/16.
 */
@Singleton
@Component(modules = {JUGGLModule.class})
public interface DAODataSource {
    CustomerDAO provideDAO();
}
