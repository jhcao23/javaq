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
        max is 255 | max is 4000
        faster | slower
        static memory allocation | dynamic mem allo
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
050.    how to list all the indexes of a table?
        -   `show index from table_name;`                                                                    
        
        
         
               
              
              
        
        
           