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
 * ResortsListResorts
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-30T04:22:21.099Z[GMT]")
public class ResortsListResorts {
  @SerializedName("resortName")
  private String resortName = null;

  public ResortsListResorts resortName(String resortName) {
    this.resortName = resortName;
    return this;
  }

   /**
   * Get resortName
   * @return resortName
  **/
  @Schema(description = "")
  public String getResortName() {
    return resortName;
  }

  public void setResortName(String resortName) {
    this.resortName = resortName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResortsListResorts resortsListResorts = (ResortsListResorts) o;
    return Objects.equals(this.resortName, resortsListResorts.resortName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resortName);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResortsListResorts {\n");
    
    sb.append("    resortName: ").append(toIndentedString(resortName)).append("\n");
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
