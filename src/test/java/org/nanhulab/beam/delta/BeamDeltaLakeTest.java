/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nanhulab.beam.delta;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.Test;

public class BeamDeltaLakeTest {
  private String DELTA_PATH =      "/tmp/beam/datalake/delta";
  private String DELTA =   "delta";

  private String OUTPUT_PATH = "/tmp/beam/output/delta/user.txt";

  /**
   * initial Delta Lake table. Delta Lake table should be created, then beam write data to it.
   */
  @Test
  public void initDeltaTable(){
    SparkSession sparkSession = SparkSession.builder().config(getDeltaSparkConf()).getOrCreate();
    sparkSession.sql("CREATE TABLE User (" +
        " id INTEGER," +
        " user_name STRING," +
        " user_age INTEGER," +
        " user_remark STRING)" +
        " USING DELTA" +
        " LOCATION '" + DELTA_PATH + "' ") ;

    sparkSession.close();
  }

  /**
   * beam write data to Delta Lake
   */
  @Test
  public void testDeltaWrite(){
    BeamDataLakeUtil.write2DataLake(DELTA, DELTA_PATH, getDeltaSparkConf(), null, 5);
  }

  /**
   * beam read data from Delta Lake
   */
  @Test
  public void testDeltaRead(){
    BeamDataLakeUtil.readFromDataLake( DELTA, DELTA_PATH, getDeltaSparkConf(), OUTPUT_PATH);
  }

  private SparkConf getDeltaSparkConf(){
    SparkConf sparkConf = new SparkConf().setAppName("delta").setMaster("local")
        .set("spark.sql.extensions","io.delta.sql.DeltaSparkSessionExtension")
        .set("spark.sql.catalog.spark_catalog","org.apache.spark.sql.delta.catalog.DeltaCatalog");
    return sparkConf;
  }

}
