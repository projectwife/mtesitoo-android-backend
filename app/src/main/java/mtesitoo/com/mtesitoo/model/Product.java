package mtesitoo.com.mtesitoo.model;

import android.net.Uri;

import java.math.BigDecimal;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Model object for a Mtesitoo product.
 */
@Immutable
public class Product {
  private final String name;
  private final String description;
  private final String price;
  private final Uri thumbnail;
  /**
   * Only exists if this is a special offer.
   */
  @Nullable
  private final BigDecimal specialPrice;
  // TODO(danieldanciu): OpenCart doesn't support multi-sellers at this point. We need to add
  // support for this.
  private final Seller seller;

  /**
   *
   * @param name product name (e.g. apples)
   * @param description product descriptionds (e.g. freshly picked, sweet-sour)
   * @param price product price per quantity (e.g. $2.5)
   * @param thumbnail url of the product's thumbnail
   * @param specialPrice special price, if this is a special offer, {@code null otherwise}
   * @param seller
   */
  public Product(String name, String description, String price, Uri thumbnail,
      @Nullable BigDecimal specialPrice, Seller seller) {
    this.name = name;
    this.description = description;

    this.price = price;
    this.thumbnail = thumbnail;
    this.specialPrice = specialPrice;
    this.seller = seller;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getPrice() {
    return price;
  }

  public Uri getThumbnail() {
    return thumbnail;
  }

  @Nullable
  public BigDecimal getSpecialPrice() {
    return specialPrice;
  }

  public Seller getSeller() {
    return seller;
  }

  @Override
  public String toString() {
    return "Product{" + "name='" + name + '\'' + ", description='" + description + '\''
        + ", price='" + price + '\'' + ", thumbnail=" + thumbnail + ", specialPrice="
        + specialPrice + ", seller=" + seller + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Product product = (Product) o;

    if (!name.equals(product.name))
      return false;
    if (description != null ? !description.equals(product.description)
        : product.description != null)
      return false;
    return !(seller != null ? !seller.equals(product.seller) : product.seller != null);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (seller != null ? seller.hashCode() : 0);
    return result;
  }
}
