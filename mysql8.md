# MySQL 8 note

000.    [official MySQL 8 doc](https://dev.mysql.com/doc/refman/8.0/en/)

001.    `i-am-a-dummy` flag tells the DB engine to `update|delete` with specified `where` condition such as `where emp_no = 1`. 
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
        - ISAM allows records to access randomly and sequentially.
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
        no `BLOB|TEXT` | allow
        no support for `AUTO_INCREMENT` | support
        Indexes should be NOT NULL | allow no-index 
        deleted when server is shutdown | deleted when client session is closed
        shared between clients | not shared            
013.    how to represent `MONEY` data type in the DDL?
        - use `DECIMAL(n, 2)`.      
        
        
         
               
              
              
        
        
           