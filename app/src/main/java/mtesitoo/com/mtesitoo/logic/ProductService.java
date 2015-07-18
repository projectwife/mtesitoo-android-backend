package mtesitoo.com.mtesitoo.logic;

import java.util.List;

import mtesitoo.com.mtesitoo.api.Callback;
import mtesitoo.com.mtesitoo.model.Product;

/**
 * Service for retrieving products (e.g. specials, search, etc.)
 * The callback results are returned on the main thread.
 */
public interface ProductService {
  void getSpecials(Callback<List<Product>> callback);
}
