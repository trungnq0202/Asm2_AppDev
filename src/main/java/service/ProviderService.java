package service;

import entity.Provider;
import entity.Staff;

import java.util.List;

public interface ProviderService {
    public List<Provider> findAll();
    public Provider findById(int id);
    public List<Provider> findByName(String name);
    public Provider save(Provider model);
    public Provider update(Provider model);
    public int delete(int id);
}
