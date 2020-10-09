package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * TopTenTopTenSkiers
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-30T04:22:21.099Z[GMT]")
public class TopTenTopTenSkiers {
  @SerializedName("skierID")
  private String skierID = null;

  @SerializedName("VertcialTotal")
  private Integer vertcialTotal = null;

  public TopTenTopTenSkiers skierID(String skierID) {
    this.skierID = skierID;
    return this;
  }

   /**
   * Get skierID
   * @return skierID
  **/
  @Schema(example = "888899", description = "")
  public String getSkierID() {
    return skierID;
  }

  public void setSkierID(String skierID) {
    this.skierID = skierID;
  }

  public TopTenTopTenSkiers vertcialTotal(Integer vertcialTotal) {
    this.vertcialTotal = vertcialTotal;
    return this;
  }

   /**
   * Get vertcialTotal
   * @return vertcialTotal
  **/
  @Schema(example = "30400", description = "")
  public Integer getVertcialTotal() {
    return vertcialTotal;
  }

  public void setVertcialTotal(Integer vertcialTotal) {
    this.vertcialTotal = vertcialTotal;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopTenTopTenSkiers topTenTopTenSkiers = (TopTenTopTenSkiers) o;
    return Objects.equals(this.skierID, topTenTopTenSkiers.skierID) &&
        Objects.equals(this.vertcialTotal, topTenTopTenSkiers.vertcialTotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skierID, vertcialTotal);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopTenTopTenSkiers {\n");
    
    sb.append("    skierID: ").append(toIndentedString(skierID)).append("\n");
    sb.append("    vertcialTotal: ").append(toIndentedString(vertcialTotal)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
