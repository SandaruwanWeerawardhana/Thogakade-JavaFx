package Service;

import Service.Custom.Impl.CustomerServiceImpl;
import Service.Custom.Impl.ItemServiceImpl;
import Util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null ? instance=new ServiceFactory():instance;
    }
    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case CUSTOMER:return (T) CustomerServiceImpl.getInstance();
            case ITEM:return (T) new ItemServiceImpl();
        }
        return null;
    }
}
