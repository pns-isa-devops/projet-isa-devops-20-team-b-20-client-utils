package client.utils.api;

public abstract class ServiceAPI {

    public ServiceAPI(String host, String port) {
        initializeService(host, port);
    }

    protected abstract void initializeService(String host, String port);

}
