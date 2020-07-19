# MySQL 8 note

000.    [official MySQL 8 doc](https://dev.mysql.com/doc/refman/8.0/en/)

001.    what does `i-am-a-dummy` flag do?
        - It tells the DB engine to `update|delete` with specified `where` condition such as `where emp_no = 1`.
        - alternative is `SET sql_safe_updates=1`. 
        DO NOT USE syntax without `where` clause.        
002.    `REGEXP`: 
        + `^` matching starts
        + `*` 0 or more matched char
        + `+` 1 or more matched char
        + `?` 0 or 1 matched char
        + `[abcdefg]` match any 1 of them from a to g
        + `.` any single one
        + `a|b` or - match either a or b
003.    what is federated table?
        - Federated table is a table pointing to another table on another DB of another server (DB instance).
004.    what is diff between primary key and candidate key?
        - candidate key is a column set that uniquely identify the row.
        - a table might have multiple candidate keys.
        - candidate key column might have null value.
        - primary key is unique and non null, which identify a row of the table.
        - primary key is candidate key.
005.    compare InnoDB vs ISAM vs MyISAM?
        - InnoDB, as a default engine, is ACID and transactional and supports foreign key.
        - InnoDB stores the tables in tablespace, which might be difficult to optimize in future.
        - ISAM allows records to access randomly and sequentially and stores data in disk:
            + The `.frm` file stores the _table_ definition
            + The _data_ file has a `.MYD` (MYData) extension
            + The _index_ file has a `.MYI` (MYIndex) extension
        - MyISAM is based on the older ISAM engine that store each table in a separate file, which can be further compressed.               
006.    What are the different column comparison operators in MySQL?        
        - `=, >, >=, <, <=, >>, <<, <=>`, `AND`, `OR`, `LIKE`.
007.    how to use `limit` in MySQL?        
        - `order by xxx limit n` selects the top n records.
        - `limit [offset] row_count` such as `order by xxx limit y, n` means retrieve n records starting from y.
008.    compare `NOW()` and `CURRENT_DATE`? 
        - `now()` returns  `YYYY-MM-DD hh:mm:ss` or `YYYYMMDDhhmmss` format.
        - `CURRENT_DATE = CURRENT_DATE() = CURDATE() = CURRENT_DATE` returns in `YYYY-MM-DD` or `YYYYMMDD` format.
009.    how many columns can a primary key contain?
        - up to **16**.      
010.    compare `float` and `double`?
        - precision: float is **4 bytes** and double is **8 bytes**.
        - As of MySQL 8.0.17, the nonstandard FLOAT(M,D) and DOUBLE(M,D) syntax is deprecated and support for it will be removed in a future MySQL version.
        - MySQL 8 uses `FLOAT|DOUBLE PRECISION`, unless the `REAL_AS_FLOAT` SQL mode is enabled.
        - MySQL 8 treats `real` as `double precision`.
        - [There are Problems with Floating-Point Values](https://dev.mysql.com/doc/refman/8.0/en/problems-with-float.html)
011.    compare `CHAR` vs `VARCHAR`?

        `CHAR` | `VARCHAR`        
        ---    | ---        
        fixed length | variable length
        max is 255 | max is 65535
        faster | slower
        static memory allocation | dynamic mem allocation
012.    compare heap table and temporary table?  
        
        `heap` | `temporary` table
        ---    | ---
        a storage engine in MEMORY | keep transient data
        `SET max_heap_table_size = 1024*1024; CREATE TABLE t1 (id INT, UNIQUE(id)) ENGINE = MEMORY;` | `create temporary table` with new `privilege`.
        use for high speed | use for temporarily
        no `BLOB or TEXT` | allow
        no support for `AUTO_INCREMENT` | support
        Indexes should be NOT NULL | allow no-index 
        deleted when server is shutdown | deleted when client session is closed
        shared between clients | not shared            
013.    how to represent `MONEY` data type in the DDL?
        - use `DECIMAL(n, 2)`. 
014.    compare `CHAR_LENGTH` vs `LENGTH`?
        - encoding matters: they are the same for Latin characters.
         
        `CHAR_LENGTH() = CHARACTER_LENGTH() ` | `LENGTH()`
        ---             | ---
        number of characters in argument |  length of a string in bytes 
015.    what does `SELECT NULL = NULL` return?
        - `NULL`
        - the `NULL` value is never true in comparison `=` to any other value, even `NULL`.
        - To search for `NULL` values, you must use the `IS NULL` test 
        - so `SELECT NULL = NULL` returns 1.
016.    how to find the MySQL RDBMS version?
        - `SELECT VERSION();`
017.    compare `ENUM` vs `SET`?
        `ENUM` | `SET`
        ---    | ---
        1 of them | 1+ of the values
        can be `''` or `NULL` | 
        cannot be an expression | ?
         | query can use `FIND_IN_SET` and `LIKE`     
018.    talk about basic data types?
        + `numeric`: 
            - `bit`: `Bit`
            - `integer`: `Integer|Int, SmallInt, TinyInt, MediumInt, BigInt`
            - `fixed-point`: `Decimal, Numeric`
            - `float-point`: `Float, Double, Real`
        + `String`: 
            - regular: `Char, Varchar, Binary, VarBinary, ENUM, SET`
            - `BLOB` (byte strings): `TINYBLOB, BLOB, MEDIUMBLOB, LONGBLOB`
            - `TEXT` (character strings): `TINYTEXT, TEXT, MEDIUMTEXT, LONGTEXT`
        + `Date|Time`: `Date, DateTime, Timestamp, Year, Time`
019.    what does `UPDATE CURRENT_TIMESTAMP` do?
        - Use of `DEFAULT CURRENT_TIMESTAMP` and `ON UPDATE CURRENT_TIMESTAMP` is specific to `TIMESTAMP` and `DATETIME`.
        - `DEFAULT CURRENT_TIMESTAMP`: the column has the current timestamp for its default value.
        - `ON UPDATE CURRENT_TIMESTAMP`: the column is automatically updated to the current timestamp.
020.    what is the command to login mysql server?
        - `mysql -h host -u user -p`
        - `mysql --port=13306 --host=localhost`
        - `mysql -h localhost -u user -p mydb -P 3306`
        - use `.mylogin.cnf` option file.            
        - show current port: `SHOW GLOBAL VARIABLES LIKE 'PORT';`
021.    how to control the max size of the heap or temp table:
        - use `max_heap_table_size | tmp_table_size` as the mysql server config variable.
022.    how to find the last id inserted?
        - use `SELECT LAST_INSERT_ID();`
        - it will return first id if last insert is a batch of records.
023.    compare `_` vs `%` in `LIKE` condition?
        - `_` matches exactly 1 character.
        - `%` matches 0 or more.
024.    how to run in batch mode?
        - `mysql -h host -u user -p < batch-file`
        - `mysql < batch-file | more`           
        - `mysql < batch-file > mysql.out`
        - use `mysql -t` to get an **interactive** output
        - To echo to the output the statements that are executed, use `mysql -v`.
        - `source filename;`
        - `\. filename`             
025.    What are the objects can be created using `CREATE` statement?
        - DATABASE
        - USER
        - EVENT
        - PROCEDURE
        - FUNCTION
        - TRIGGER
        - TABLE
        - INDEX
        - VIEW   
026.    How many types of TRIGGERS are allowed in MySql table?
        - 6: `BEFORE|AFTER` `INSERT|DELETE|UPDATE` 
027.    talk about `TRUNCATE` with comparing with `DELETE` and `DROP`?
        - `TRUNCATE TABLE` empties a table completely
        - high performance
        - It requires the `DROP privilege`. 
        - Logically, `TRUNCATE TABLE` is similar to a `DELETE` statement that deletes all rows, or a sequence of `DROP TABLE` and `CREATE TABLE` statements.
        - it is classified as a **DDL** statement rather than a DML statement
        - `TRUNCATE TABLE` bypasses the DML method of deleting data
        - it does not cause `ON DELETE` **triggers** to fire
        - it cannot be performed for _InnoDB_ tables with parent-child **foreign key** relationships
        - it cannot be `rolled back` like a DML operation
028.    how to load a large `CSV` file to database?  
        - `LOAD DATA LOCAL INFILE "file_name" INTO TABLE table_name FIELDS TERMINATED by ',' LINES TERMINATED BY '\n'`        
                       
050.    how to list all the indexes of a table?
        -   `show index from table_name;`  
051.    Write a query to fetch **duplicate** records from a table using MySQL?
        ```sql
            SELECT EmpId
            FROM EmployeeSalary 
            GROUP BY EmpId
            HAVING COUNT(*) > 1;
        ```    
052.    what are the basic methodology to optimize query?
        - avoid `sp_` when creating stored procedure
        - avoid `select *`
        - avoid `%` wildcard
        - use alias for table name
        - use `DISTINCT` smartly esp when with `UNION` - better used solely  
        - use `subquery` smartly
        - take best advantages of `index` and `explain analyze` execution plan.
053.    how to `create trigger`?
        - `create trigger` syntax:
            ```sql
                CREATE
                    [DEFINER = user]
                    TRIGGER trigger_name
                    trigger_time trigger_event
                    ON tbl_name FOR EACH ROW
                    [trigger_order]
                    trigger_body
                
                trigger_time: { BEFORE | AFTER }
                
                trigger_event: { INSERT | UPDATE | DELETE }
                
                trigger_order: { FOLLOWS | PRECEDES } other_trigger_name
            ```        
        - create examples:
            + `CREATE TABLE account (acct_num INT, amount DECIMAL(10,2));`
            + `CREATE TRIGGER ins_sum BEFORE INSERT ON account FOR EACH ROW SET @sum = @sum + NEW.amount;`
            + `sql CREATE TRIGGER ins_transaction BEFORE INSERT ON account
                      FOR EACH ROW PRECEDES ins_sum
                      SET
                      @deposits = @deposits + IF(NEW.amount>0,NEW.amount,0),
                      @withdrawals = @withdrawals + IF(NEW.amount<0,-NEW.amount,0);
              `
            + `mysql> delimiter //
               mysql> CREATE TRIGGER upd_check BEFORE UPDATE ON account
                      FOR EACH ROW
                      BEGIN
                          IF NEW.amount < 0 THEN
                              SET NEW.amount = 0;
                          ELSEIF NEW.amount > 100 THEN
                              SET NEW.amount = 100;
                          END IF;
                      END;//
               mysql> delimiter ;
              `
            + `CREATE TRIGGER testref BEFORE INSERT ON test1
                 FOR EACH ROW
                 BEGIN
                   INSERT INTO test2 SET a2 = NEW.a1;
                   DELETE FROM test3 WHERE a3 = NEW.a1;
                   UPDATE test4 SET b4 = b4 + 1 WHERE a4 = NEW.a1;
                 END;
              `
        - In an `INSERT` trigger, only `NEW.col_name` can be used; there is **no old row**.
        - In a `DELETE` trigger, only `OLD.col_name` can be used; there is **no new row**.
        - In an `UPDATE` trigger, you can use `OLD.col_name` to refer to the columns of a row _before_ it is updated and `NEW.col_name` to refer to the columns of the row _after_ it is updated.
054.    how to `drop trigger`?        
        - `drop trigger` syntax:
            ```sql
              DROP TRIGGER [IF EXISTS] [schema_name.]trigger_name
            ```
        - drop example: `DROP TRIGGER test.ins_sum;`
055.    how to find the Nth record?
        - use `limit N-1, 1`
056.    how to convert a string to utf8?
        - `SELECT CONVERT('abc' USING utf8);` 
        - to list all the char set use `SHOW CHARACTER SET;` or `SELECT * FROM INFORMATION_SCHEMA.CHARACTER_SETS;`
057.    how to `create index`?
        - syntax:
        ```sql
            CREATE [UNIQUE | FULLTEXT | SPATIAL] INDEX index_name
                [index_type]
                ON tbl_name (key_part,...)
                [index_option]
                [algorithm_option | lock_option] ...
            
            key_part: {col_name [(length)] | (expr)} [ASC | DESC]
            
            index_option: {
                KEY_BLOCK_SIZE [=] value
              | index_type
              | WITH PARSER parser_name
              | COMMENT 'string'
              | {VISIBLE | INVISIBLE}
              | ENGINE_ATTRIBUTE [=] 'string'
              | SECONDARY_ENGINE_ATTRIBUTE [=] 'string'
            }
            
            index_type:
                USING {BTREE | HASH}
            
            algorithm_option:
                ALGORITHM [=] {DEFAULT | INPLACE | COPY}
            
            lock_option:
                LOCK [=] {DEFAULT | NONE | SHARED | EXCLUSIVE}
        ```
        - example:
            + `CREATE INDEX part_of_name ON customer (name(10));`
            + `CREATE INDEX idx2 ON t1 ((col1 + col2), (col1 - col2), col1);`
            + `ALTER TABLE t1 ADD INDEX ((col1 * 40) DESC);`
            + `CREATE TABLE t1 (col1 INT, col2 INT, INDEX func_index ((ABS(col1))));`
            + `ALTER TABLE employees ADD INDEX idx (generated_col);`
            + `ALTER TABLE customers ADD INDEX zips( (CAST(custinfo->'$.zip' AS UNSIGNED ARRAY)) );`
            + `CREATE INDEX zips ON customers ( (CAST(custinfo->'$.zip' AS UNSIGNED ARRAY)) );`                                                                                                     
058.    list all mysql 8 **aggregate** functions?
        + basic: 
            - `AVG, MAX, MIN, SUM([DISTINCT] expr) [over_clause]`
            - `COUNT(expr) [over_clause]`, `COUNT(DISTINCT expr,[expr...])`
            - `BIT_AND, BIT_OR, BIT_XOR(expr) [over_clause]`
        + grouping:
            - `GROUP_CONCAT(expr)`
            - `JSON_ARRAYAGG(col_or_expr) [over_clause]`
            - `JSON_OBJECTAGG(key, value) [over_clause]` 
            - `GROUPING(expr [, expr] ...)` only within `select` or `having` clause - a _miscellaneous function_ actually  
        + stats:
            - `STD(expr) [over_clause]`
            - `STDDEV(expr) [over_clause]`
            - `STDDEV_POP(expr) [over_clause]`
            - `STDDEV_SAMP(expr) [over_clause]`
            - `VAR_POP(expr) [over_clause]`
            - `VAR_SAMP(expr) [over_clause]`
            - `VARIANCE(expr) [over_clause]`
059.    how to `create procedure`?
        + the syntax:
            ```sql
                CREATE
                    [DEFINER = user]
                    PROCEDURE sp_name ([ [ IN | OUT | INOUT ] param_name type [,...] ])
                    [characteristic ...] routine_body
            
                characteristic: {
                    COMMENT 'string'
                  | LANGUAGE SQL
                  | [NOT] DETERMINISTIC
                  | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
                  | SQL SECURITY { DEFINER | INVOKER }
                }
            ```     
        + example:
            ```sql
                delimiter //
                CREATE PROCEDURE citycount (IN country CHAR(3), OUT cities INT)
                     BEGIN
                       SELECT COUNT(*) INTO cities FROM world.city
                       WHERE CountryCode = country;
                     END//
                delimiter ;
                
                CALL citycount('JPN', @cities);
                
                SELECT @cities;
                CALL citycount('FRA', @cities);
                SELECT @cities;
          
                CREATE DEFINER = 'admin'@'localhost' PROCEDURE account_count()
                BEGIN
                  SELECT 'Number of accounts:', COUNT(*) FROM mysql.user;
                END;
          
                CREATE DEFINER = 'admin'@'localhost' PROCEDURE account_count()
                SQL SECURITY INVOKER
                BEGIN
                  SELECT 'Number of accounts:', COUNT(*) FROM mysql.user;
                END;                 
            ```    
060.    how to `create function`?
        - syntax:
            ```sql
                CREATE
                    [DEFINER = user]
                    FUNCTION sp_name ([param_name type[,...]])
                    RETURNS type
                    [characteristic ...] routine_body
                characteristic: {
                    COMMENT 'string'
                  | LANGUAGE SQL
                  | [NOT] DETERMINISTIC
                  | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
                  | SQL SECURITY { DEFINER | INVOKER }
                }
            ```   
        - example:
            ```sql
              CREATE FUNCTION hello (s CHAR(20))
              RETURNS CHAR(50) DETERMINISTIC
                  RETURN CONCAT('Hello, ',s,'!');
          
              SELECT hello('world');
          
          
            ```       
        
         
               
              
              
        
        
           