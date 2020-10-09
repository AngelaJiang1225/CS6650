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
 * SkierVertical
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-30T04:22:21.099Z[GMT]")
public class SkierVertical {
  @SerializedName("resortID")
  private String resortID = null;

  @SerializedName("totalVert")
  private Integer totalVert = null;

  public SkierVertical resortID(String resortID) {
    this.resortID = resortID;
    return this;
  }

   /**
   * Get resortID
   * @return resortID
  **/
  @Schema(example = "Mission Ridge", description = "")
  public String getResortID() {
    return resortID;
  }

  public void setResortID(String resortID) {
    this.resortID = resortID;
  }

  public SkierVertical totalVert(Integer totalVert) {
    this.totalVert = totalVert;
    return this;
  }

   /**
   * Get totalVert
   * @return totalVert
  **/
  @Schema(example = "56734", description = "")
  public Integer getTotalVert() {
    return totalVert;
  }

  public void setTotalVert(Integer totalVert) {
    this.totalVert = totalVert;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkierVertical skierVertical = (SkierVertical) o;
    return Objects.equals(this.resortID, skierVertical.resortID) &&
        Objects.equals(this.totalVert, skierVertical.totalVert);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resortID, totalVert);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SkierVertical {\n");
    
    sb.append("    resortID: ").append(toIndentedString(resortID)).append("\n");
    sb.append("    totalVert: ").append(toIndentedString(totalVert)).append("\n");
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
