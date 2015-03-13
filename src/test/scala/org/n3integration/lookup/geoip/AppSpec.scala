/*
 *  Copyright 2015 n3integration
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.n3integration.lookup.geoip

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test._
import org.n3integration.lookup.geoip.App.GeoIPLookupApp

class AppSpec extends FlatSpecHelper {

  val app = new GeoIPLookupApp
  override val server = new FinatraServer
  server.register(app)

  "GET /127.0.0.1" should "respond with home" in {
    get("/127.0.0.1")
    response.code should equal(200)
    response.body.contains("Home") should equal(true)
  }

  "GET /192.168.1.2" should "not be found" in {
    get("/192.168.1.2")
    response.code should equal(404)
  }

  "GET /garbageIn" should "respond with garbage out" in {
    get("/garbageIn")
    response.code should equal(400)
  }
}