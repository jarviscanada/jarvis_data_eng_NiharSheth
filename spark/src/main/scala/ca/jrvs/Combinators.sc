import scala.io.Source

/**
 * Count number of elements
 * Get the first element
 * Get the last element
 * Get the first 5 elements
 * Get the last 5 elements
 *
 * hint: use the following methods
 * head
 * last
 * size
 * take
 * tails
 */
val ls = List.range(0,10)
//write you solution here
ls.size // Number of elements
ls.head // First element
ls.last // Last element
ls.take(5) // First 5 elements
ls.takeRight(5) // Last 5 elements

/**
 * Double each number from the numList and return a flatten list
 * e.g.res4: List[Int] = List(2, 3, 4)
 *
 * Compare flatMap VS ls.map().flatten
 */
val numList = List(List(1,2), List(3))
//write you solution here
numList.flatten.map((num: Int) => num*2)
numList.map((l: List[Int]) => l.map(_*2)).flatten
numList.flatMap(l => l.map(_*2))

/**
 * Sum List.range(1,11) in three ways
 * hint: sum, reduce, foldLeft
 *
 * Compare reduce and foldLeft
 * https://stackoverflow.com/questions/7764197/difference-between-foldleft-and-reduceleft-in-scala
 */
//write you solution here
val numList2 = List.range(1, 11)
numList2.sum
numList2.foldLeft(0)((a: Int, b: Int) => a+b)
numList2.reduce((a, b) => a+b)

/**
 * Practice Map and Optional
 *
 * Map question1:
 *
 * Compare get vs getOrElse (Scala Optional)
 * countryMap.get("Amy");
 * countryMap.getOrElse("Frank", "n/a");
 */
val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
countryMap.get("Amy") // Will return "Canada"
countryMap.get("edward") // Will return None since key is not found
countryMap.getOrElse("edward", "n/a") // Will return "n/a" since key is not found

/**
 * Map question2:
 *
 * create a list of (name, country) tuples using `countryMap` and `names`
 * e.g. res2: List[(String, String)] = List((Amy,Canada), (Sam,US), (Eric,n/a), (Amy,Canada))
 */
val names = List("Amy", "Sam", "Eric", "Amy")
//write you solution here
names.map((name: String) => name -> countryMap.getOrElse(name, "n/a"))

/**
 * Map question3:
 *
 * count number of people by country. Use `n/a` if the name is not in the countryMap  using `countryMap` and `names`
 * e.g. res0: scala.collection.immutable.Map[String,Int] = Map(Canada -> 2, n/a -> 1, US -> 1)
 * hint: map(get_value_from_map) ; groupBy country; map to (country,count)
 */
//write you solution here
names
  .map((name:String) => countryMap.getOrElse(name, "n/a"))
  .groupBy(country => country)
  .map({ case (country, count) => country -> count.size })

/**
 * number each name in the list from 1 to n
 * e.g. res3: List[(Int, String)] = List((1,Amy), (2,Bob), (3,Chris))
 */
val names2 = List("Amy", "Bob", "Chris", "Dann")
//write you solution here
List.range(1, names2.size).zip(names2)

/**
 * SQL questions1:
 *
 * read file lines into a list
 * lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
 */
//write you solution here
val lines = Source.fromFile("/Users/nihar/dev/jarvis_data_eng_NiharSheth/spark/src/main/resources/employees.csv")
  .getLines()
  .flatMap(_.split(","))
  .toList

/**
 * SQL questions2:
 *
 * Convert lines to a list of employees
 * e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
 */
//write you solution here
class Employee(var id: Int, var name: String, var city: String, var age: Int) {
  override def toString: String = s"Employee(%s, %s, %s, %s)".format(id, name, city, age)
}

val employees = Source.fromFile("/Users/nihar/dev/jarvis_data_eng_NiharSheth/spark/src/main/resources/employees.csv")
  .getLines()
  .map(_.split(","))
  .toList
  .drop(1)
  //.map({ case Array(id, name, city, age) => new Employee(id.toInt, name, city, age.toInt) })
  .map((emp: Array[String]) => new Employee(id = emp(0).toInt, name = emp(1), city = emp(2), age = emp(3).toInt))

/**
 * SQL questions3:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
//write you solution here
//val upperCity = employees.foreach((employee: Employee) => employee.city = employee.city.toUpperCase())
val upperCity = employees.map((employee: Employee) => { new Employee(id = employee.id, name = employee.name, city = employee.city.toUpperCase(), age = employee.age)})
upperCity

/**
 * SQL questions4:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
//write you solution here
upperCity.filter((employee: Employee) => employee.city == "TORONTO")

/**
 * SQL questions5:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
//write you solution here
val cityNum = upperCity.groupBy((employee: Employee) => employee.city).map({ case (city, employees) => city -> employees.size })

/**
 * SQL questions6:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */
//write you solution here
upperCity.groupBy((employee: Employee) => employee.city -> employee.age).map({ case (city, employees) => city -> employees.size })