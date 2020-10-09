package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.ResortsListResorts;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * ResortsList
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-30T04:22:21.099Z[GMT]")
public class ResortsList {
  @SerializedName("resorts")
  private List<ResortsListResorts> resorts = null;

  public ResortsList resorts(List<ResortsListResorts> resorts) {
    this.resorts = resorts;
    return this;
  }

  public ResortsList addResortsItem(ResortsListResorts resortsItem) {
    if (this.resorts == null) {
      this.resorts = new ArrayList<ResortsListResorts>();
    }
    this.resorts.add(resortsItem);
    return this;
  }

   /**
   * Get resorts
   * @return resorts
  **/
  @Schema(description = "")
  public List<ResortsListResorts> getResorts() {
    return resorts;
  }

  public void setResorts(List<ResortsListResorts> resorts) {
    this.resorts = resorts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResortsList resortsList = (ResortsList) o;
    return Objects.equals(this.resorts, resortsList.resorts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resorts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResortsList {\n");
    
    sb.append("    resorts: ").append(toIndentedString(resorts)).append("\n");
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
