/*
package com.example.java8.javaRegex;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ExperienceTreasure implements Cloneable, Serializable {
   public static final Boolean IS_DELETED;
   public static final Boolean NOT_DELETED;
   private Integer attrib;
   private Integer maxLevel;
   @JsonDeserialize(
      using = LocalDateTimeDeserializer.class
   )
   @JsonSerialize(
      using = LocalDateTimeSerializer.class
   )
   @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private LocalDateTime addTime;
   @JsonDeserialize(
      using = LocalDateTimeDeserializer.class
   )
   @JsonSerialize(
      using = LocalDateTimeSerializer.class
   )
   @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private LocalDateTime updateTime;
   private Boolean deleted;
   private static final long serialVersionUID = 1L;

   public Integer getAttrib() {
      return this.attrib;
   }

   public void setAttrib(final Integer attrib) {
      this.attrib = attrib;
   }

   public Integer getMaxLevel() {
      return this.maxLevel;
   }

   public void setMaxLevel(final Integer maxLevel) {
      this.maxLevel = maxLevel;
   }

   public LocalDateTime getAddTime() {
      return this.addTime;
   }

   public void setAddTime(final LocalDateTime addTime) {
      this.addTime = addTime;
   }

   public LocalDateTime getUpdateTime() {
      return this.updateTime;
   }

   public void setUpdateTime(final LocalDateTime updateTime) {
      this.updateTime = updateTime;
   }

   public void andLogicalDeleted(final boolean deleted) {
      this.setDeleted(deleted ? ExperienceTreasure.Deleted.IS_DELETED.value() : ExperienceTreasure.Deleted.NOT_DELETED.value());
   }

   public Boolean getDeleted() {
      return this.deleted;
   }

   public void setDeleted(final Boolean deleted) {
      this.deleted = deleted;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.getClass().getSimpleName());
      sb.append(" [");
      sb.append("Hash = ").append(this.hashCode());
        sb.append(", IS_DELETED=").append(ExperienceTreasure.IS_DELETED);
        sb.append(", NOT_DELETED=").append(ExperienceTreasure.NOT_DELETED);
        sb.append(", attrib=").append(this.attrib);
        sb.append(", maxLevel=").append(this.maxLevel);
        sb.append(", addTime=").append(this.addTime);
        sb.append(", updateTime=").append(this.updateTime);
        sb.append(", deleted=").append(this.deleted);
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (this.getClass() != that.getClass()) {
            return false;
        }
        final ExperienceTreasure other = (ExperienceTreasure)that;
        if (this.getAttrib() == null) {
            if (other.getAttrib() != null) {
                return false;
            }
        }
        else if (!this.getAttrib().equals(other.getAttrib())) {
            return false;
        }
        if (this.getMaxLevel() == null) {
            if (other.getMaxLevel() != null) {
                return false;
            }
        }
        else if (!this.getMaxLevel().equals(other.getMaxLevel())) {
            return false;
        }
        if (this.getAddTime() == null) {
            if (other.getAddTime() != null) {
                return false;
            }
        }
        else if (!this.getAddTime().equals(other.getAddTime())) {
            return false;
        }
        if (this.getUpdateTime() == null) {
            if (other.getUpdateTime() != null) {
                return false;
            }
        }
        else if (!this.getUpdateTime().equals(other.getUpdateTime())) {
            return false;
        }
        if (this.getDeleted() == null) {
            if (other.getDeleted() != null) {
                return false;
            }
        }
        else if (!this.getDeleted().equals(other.getDeleted())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 1;
        int result = 1;
        result = 31 * result + ((this.getAttrib() == null) ? 0 : this.getAttrib().hashCode());
        result = 31 * result + ((this.getMaxLevel() == null) ? 0 : this.getMaxLevel().hashCode());
        result = 31 * result + ((this.getAddTime() == null) ? 0 : this.getAddTime().hashCode());
        result = 31 * result + ((this.getUpdateTime() == null) ? 0 : this.getUpdateTime().hashCode());
        result = 31 * result + ((this.getDeleted() == null) ? 0 : this.getDeleted().hashCode());
        return result;
   }

   public ExperienceTreasure clone() throws CloneNotSupportedException {
      return (ExperienceTreasure)super.clone();
   }

   static {
      IS_DELETED = ExperienceTreasure.Deleted.IS_DELETED.value();
      NOT_DELETED = ExperienceTreasure.Deleted.NOT_DELETED.value();
   }

   public static enum Deleted {
      NOT_DELETED(new Boolean("0"), "未删除"),
      IS_DELETED(new Boolean("1"), "已删除");

      private final Boolean value;
      private final String name;

      private Deleted(final Boolean value, final String name) {
         this.value = value;
         this.name = name;
      }

      public Boolean getValue() {
         return this.value;
      }

      public Boolean value() {
         return this.value;
      }

      public String getName() {
         return this.name;
      }
   }

   public static enum Column {
      attrib("attrib", "attrib", "INTEGER", false),
      maxLevel("max_level", "maxLevel", "INTEGER", false),
      addTime("add_time", "addTime", "TIMESTAMP", false),
      updateTime("update_time", "updateTime", "TIMESTAMP", false),
      deleted("deleted", "deleted", "BIT", false);

      private static final String BEGINNING_DELIMITER = "`";
      private static final String ENDING_DELIMITER = "`";
      private final String column;
      private final boolean isColumnNameDelimited;
      private final String javaProperty;
      private final String jdbcType;

      public String value() {
         return this.column;
      }

      public String getValue() {
         return this.column;
      }

      public String getJavaProperty() {
         return this.javaProperty;
      }

      public String getJdbcType() {
         return this.jdbcType;
      }

      private Column(final String column, final String javaProperty, final String jdbcType, final boolean isColumnNameDelimited) {
         this.column = column;
         this.javaProperty = javaProperty;
         this.jdbcType = jdbcType;
         this.isColumnNameDelimited = isColumnNameDelimited;
      }

      public String desc() {
         return this.getEscapedColumnName() + " DESC";
      }

      public String asc() {
         return this.getEscapedColumnName() + " ASC";
      }

      public static ExperienceTreasure.Column[] excludes(final ExperienceTreasure.Column... excludes) {
         ArrayList<Column> columns = new ArrayList(Arrays.asList(values()));
         if (excludes != null && excludes.length > 0) {
            columns.removeAll(new ArrayList(Arrays.asList(excludes)));
         }

         return (ExperienceTreasure.Column[])((ExperienceTreasure.Column[])columns.toArray(new ExperienceTreasure.Column[0]));
      }

      public String getEscapedColumnName() {
         return this.isColumnNameDelimited ? "`" + this.column + "`" : this.column;
      }
   }
}
*/