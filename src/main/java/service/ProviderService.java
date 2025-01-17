package service;

import model.Provider;
import model.PaginatedList;

public interface ProviderService {
    public PaginatedList<Provider> findAll(int pageIndex, int pageSize);
    public Provider findById(int id);
    public PaginatedList<Provider> findByName(String name, int pageIndex, int pageSize);
    public PaginatedList<Provider> findByPhone(String phone, int pageIndex, int pageSize);
    public PaginatedList<Provider> findByAddress(String address, int pageIndex, int pageSize);
    public Provider save(Provider model);
    public Provider update(Provider model);
    public int delete(int id);
}
