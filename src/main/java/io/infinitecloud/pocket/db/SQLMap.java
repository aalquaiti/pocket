/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.db;

/**
 * Utility class to retrieve SQL Statements
 * @author Aymen Alquaiti
 * <p>Date: 02/02/2016</p>
 */
public class SQLMap
{    
    // <editor-fold defaultstate="collapsed" desc="Column Name">
    
    public static final String KEY_ID = "id";
    
    public static final String KEY_VERSION = "version";
    
    public static final String KEY_PRIM_CUR = "primary_currency";
    
    public static final String KEY_DEF_CAT = "default_category";
    
    public static final String KEY_NAME = "name";
    
    public static final String KEY_TYPE = "type";
    
    public static final String KEY_BAL = "balance";
    
    public static final String KEY_RATE = "rate";
    
    public static final String KEY_RECIP = "reciprocal";
    
    public static final String KEY_DECIMAL = "decimal";
    
    public static final String KEY_POS = "pos";
    
    public static final String KEY_USED = "used";
    
    public static final String KEY_SYM = "symbol";
    
    public static final String KEY_ABBR = "abbreviation";
    
    public static final String KEY_HAS_ABBR = "has_abbreviation";
    
    public static final String KEY_OPEN_BAL = "open_balance";
    
    public static final String KEY_CLOSED = "closed";
    
    public static final String KEY_CURR_ID = "curr_id";
    
    public static final String KEY_GRP_ID = "grp_id";
    
    public static final String KEY_CLASS = "class";
    
    public static final String KEY_BUDGET = "budget";
    
    public static final String KEY_HAS_BUDGET = "has_budget";
    
    public static final String KEY_CAT_ID = "cat_id";
    
    public static final String KEY_DATE = "date";
    
    public static final String KEY_COMMENT = "comment";
    
    public static final String KEY_PAYEE = "payee";
    
    public static final String KEY_TRN_ID = "trn_id";
    
    public static final String KEY_ACC_ID = "acc_id";
    
    public static final String KEY_AMOUNT = "amount";
        
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DB Creation">
    
    /**
     * Table name
     */
    public static final String TB_ACCOUNT_TYPE = "account_type";
    
    /**
     * Create table account_type
     */
    public static final String CREATE_TB_ACCOUNT_TYPE = String.format(
                    "CREATE TABLE %s (\n" +
                    "    %s   INTEGER    PRIMARY KEY\n" +
                    "                    NOT NULL,\n" +
                    "    %s   TEXT (255) NOT NULL\n" +
                    ")", TB_ACCOUNT_TYPE, KEY_ID, KEY_NAME);
    
    /**
     * Table name
     */
    public static final String TB_ACCOUNT = "account";
    
    /**
     * Creates Table account
     */
    public static final String CREATE_TB_ACCOUNT = String.format(
            "CREATE TABLE %s (\n" +
            "    %s    INTEGER    PRIMARY KEY AUTOINCREMENT\n" +
            "                     NOT NULL,\n" +        
            "    %s    TEXT (255) NOT NULL,\n" +
            "    %s    INTEGER    REFERENCES %s (%s) ON DELETE RESTRICT\n" +
            "                                        ON UPDATE CASCADE\n" +
            "                     NOT NULL,\n" +
            "    %s    INTEGER    NOT NULL\n" +
            ")", TB_ACCOUNT, KEY_ID, KEY_NAME, KEY_TYPE, TB_ACCOUNT_TYPE, KEY_ID, KEY_BAL);
    
    /**
     * Table name
     */
    public static final String TB_ACCOUNT_GROUP = "account_group";
    
    /**
     * Creates Table account_group
     */
    public static final String CREATE_TB_ACCOUNT_GROUP = String.format(
            "CREATE TABLE %s (\n" +
            "    %s   INTEGER       PRIMARY KEY AUTOINCREMENT\n" +
            "                       NOT NULL,\n" +        
            "    %s   TEXT  (255)   NOT NULL\n" +
            ")", TB_ACCOUNT_GROUP, KEY_ID, KEY_NAME);
    
    /**
     * Table name
     */
    public static final String TB_CURRENCY = "currency";
    
    /**
     * Creates Table currency
     */
    public static final String CREATE_TB_CURRENCY = String.format(
            "CREATE TABLE %s (\n" +
            "    %s    INTEGER    PRIMARY KEY\n" +
            "                     NOT NULL,\n" +        
            "    %s    TEXT (255) NOT NULL,\n" +
            "    %s    INTEGER    NOT NULL,\n" +
            "    %s    BOOLEAN    NOT NULL,\n" +
            "    %s    INTEGER    NOT NULL,\n" +
            "    %s    INTEGER    NOT NULL,\n" +
            "    %s    BOOLEAN    NOT NULL,\n" +
            "    %s    TEXT (255) NOT NULL,\n" +
            "    %s    TEXT (255) NOT NULL,\n" +
            "    %s    BOOLEAN    NOT NULL\n" +
            ")", TB_CURRENCY, KEY_ID, KEY_NAME, KEY_RATE, KEY_RECIP, KEY_DECIMAL
               , KEY_POS, KEY_USED, KEY_SYM, KEY_ABBR, KEY_HAS_ABBR);
    
    /**
     * Table normal account
     */
    public static final String TB_NORMAL_ACCOUNT = "normal_account";
    
    /**
     * Creates Table normal_account
     */
    public static final String CREATE_TB_NORMAL_ACCOUNT = String.format(
            "CREATE TABLE %s (\n" +
            "    %s      INTEGER REFERENCES %s (%s)     ON DELETE CASCADE\n" +
            "                                           ON UPDATE CASCADE\n" +
            "                    PRIMARY KEY\n" +
            "                    NOT NULL,\n" +        
            "    %s      INTEGER NOT NULL,\n" +
            "    %s      BOOLEAN NOT NULL,\n" +
            "    %s      INTEGER REFERENCES %s (%s)     ON DELETE RESTRICT\n" +
            "                                           ON UPDATE CASCADE\n" +
            "                    NOT NULL,\n" +        
            "    %s      INTEGER REFERENCES %s (%s)     ON DELETE SET NULL\n" +
            "                                           ON UPDATE CASCADE\n" +
            ")", TB_NORMAL_ACCOUNT, KEY_ID, TB_ACCOUNT, KEY_ID, KEY_OPEN_BAL,
                    KEY_CLOSED, KEY_CURR_ID, TB_CURRENCY, KEY_ID, KEY_GRP_ID, TB_ACCOUNT_GROUP, KEY_ID);
    
    /**
     * Table name
     */
    public static final String TB_PEOPLE_GROUP = "people_group";
    
    /**
     * Creates Table people_group
     */
    public static final String CREATE_TB_PEOPLE_GROUP = String.format(
            "CREATE TABLE %s (\n" +
            "    %s             INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
            "    %s             VARCHAR (255) NOT NULL\n" +
            "                                 UNIQUE\n" +
            ")", TB_PEOPLE_GROUP, KEY_ID, KEY_NAME);
    
    
    /**
     * Table name
     */
    public static final String TB_PEOPLE_ACCOUNT = "people_account";
    
    /**
     * Creates Table people_account
     */
    public static final String CREATE_TB_PEOPLE_ACCOUNT = String.format(
            "CREATE TABLE %s (\n" +
            "    %S           INTEGER REFERENCES %s (%s)       ON DELETE CASCADE\n" +
            "                                                  ON UPDATE CASCADE\n" +
            "                         NOT NULL\n" +
            "                         PRIMARY KEY,\n" +
            "    %s           INTEGER NOT NULL,\n" +
            "    %s           INTEGER NOT NULL\n" +
            "                         REFERENCES %s (%s)       ON DELETE RESTRICT\n" +
            "                                                  ON UPDATE CASCADE\n" +
            "    %s           INTEGER NOT NULL\n" + 
            "                         REFERENCES %s (%s)       ON DELETE RESTRICT\n" +
            "                                                  ON UPDATE CASCADE\n" +

            ");", TB_PEOPLE_ACCOUNT, KEY_ID, TB_ACCOUNT, KEY_ID, KEY_OPEN_BAL,
                    KEY_CURR_ID, TB_CURRENCY, KEY_ID, KEY_GRP_ID, TB_PEOPLE_GROUP, KEY_ID);
    
    /**
     * Table name
     */
    public static final String TB_CATEGORY_CLASS = "category_class";
    
    public static final String CREATE_TB_CATEGORY_CLASS = String.format("CREATE TABLE %s (\n" +
            "    %s   INTEGER    PRIMARY KEY\n" +
            "                    NOT NULL,\n" +
            "    %s   TEXT (255) NOT NULL\n" +
            ");", TB_CATEGORY_CLASS, KEY_ID, KEY_NAME);
    
    /**
     * Table name
     */
    public static final String TB_CATEGORY = "category";
    
    /**
     * Creates Table category
     */
    public static final String CREATE_TB_CATEGORY = String.format(
            "CREATE TABLE %s (\n" +
            "    %s      INTEGER REFERENCES %s (%s)     ON DELETE CASCADE\n" +
            "                                           ON UPDATE CASCADE\n" +
            "                    PRIMARY KEY,\n" +
            "    %s      INTEGER NOT NULL\n" +
            "                    REFERENCES %s (%s)     ON DELETE RESTRICT\n" +
            "                                           ON UPDATE CASCADE\n" +        
            
            "                    NOT NULL,\n" +        
            "    %s      INTEGER NOT NULL,\n" +
            "    %s      BOOLEAN NOT NULL\n" +
            ")", TB_CATEGORY, KEY_ID, TB_ACCOUNT, KEY_ID, KEY_CLASS, TB_CATEGORY_CLASS,
                                KEY_ID, KEY_BUDGET, KEY_HAS_BUDGET);
    
    /**
     * Table name
     */
    public static final String TB_SUB_CATEGORY = "sub_category";
    
    /**
     * Creates Table sub_category
     */
    public static final String CREATE_TB_SUB_CATEGORY = String.format(
            "CREATE TABLE %s (\n" +
            "    %s     INTEGER   REFERENCES %s (%s)    ON DELETE CASCADE\n" +
            "                                           ON UPDATE CASCADE\n" +
            "                     PRIMARY KEY\n" +
            "                     NOT NULL,\n" +        
            "    %s     INTEGER   REFERENCES %s (%s)    ON DELETE CASCADE\n" +
            "                                           ON UPDATE CASCADE\n" +
            "                     NOT NULL\n" +        
            ")", TB_SUB_CATEGORY, KEY_ID, TB_ACCOUNT, KEY_ID, KEY_CAT_ID,
                            TB_CATEGORY, KEY_ID);
    
    /**
     * Table name
     */
    public static final String TB_PAYEE = "payee";
    
    public static final String CREATE_TB_PAYEE = String.format(
            "CREATE TABLE %s (\n" +
            "    %s   INTEGER    REFERENCES %s (%s) ON DELETE CASCADE\n" +
            "                                       ON UPDATE CASCADE\n" +
            "                    PRIMARY KEY    AUTOINCREMENT\n" +
            "                    NOT NULL,\n" +        
            "    %s   TEXT (255) NOT NULL\n" +
            ");", TB_PAYEE, KEY_ID, TB_ACCOUNT, KEY_ID, KEY_NAME);
    
    /**
     * Table name
     */
    public static final String TB_TRANSACTION_TYPE = "trans_type";
    
    /**
     * Creates Table trans_type
     */
    public static final String CREATE_TB_TRANSACTION_TYPE = String.format(
            "CREATE TABLE %s (\n" +
            "    %s     INTEGER PRIMARY KEY\n" +
            "                   NOT NULL,\n" +
            "    %s     TEXT    NOT NULL\n" +
            ");", TB_TRANSACTION_TYPE, KEY_ID, KEY_NAME);
    
    /**
     * Table name
     */
    public static final String TB_TRANSACTION = "trans";
    
    /**
     * Creates Table trans
     */
    public static final String CREATE_TB_TRANSACTION = String.format(
            "CREATE TABLE %s (\n" +
            "    %s      INTEGER    PRIMARY KEY AUTOINCREMENT\n" +
            "                       NOT NULL,\n" +        
            "    %s      INTEGER    NOT NULL,\n" +
            "    %s      TEXT (500) NOT NULL,\n" +
            "    %s      INTEGER    REFERENCES %s (%s) ON DELETE SET NULL\n" +
            "                                          ON UPDATE CASCADE,\n" +
            "    %s      INTEGER    REFERENCES %s (%s) ON DELETE RESTRICT\n" +
            "                                          ON UPDATE CASCADE\n" +
            "                       NOT NULL\n" +
            ")", TB_TRANSACTION, KEY_ID, KEY_DATE, KEY_COMMENT, KEY_PAYEE, TB_PAYEE,
                    KEY_ID, KEY_TYPE, TB_TRANSACTION_TYPE, KEY_ID);
    
    /**
     * Table name
     */
    public static final String TB_TRANSACTION_FX = "trans_fx";
    
    public static final String CREATE_TB_TRANSACTION_FX = String.format(
            "CREATE TABLE %s (\n" +
            "    %s   INTEGER NOT NULL\n" +
            "                 PRIMARY KEY\n" +
            "                 REFERENCES %s (%s)    ON DELETE CASCADE\n" +
            "                                       ON UPDATE CASCADE,\n" +
            "    %s   INTEGER NOT NULL,\n" +
            "    %s   INTEGER NOT NULL,\n" +
            "    %s   BOOLEAN NOT NULL\n" +
            ");", TB_TRANSACTION_FX, KEY_ID, TB_TRANSACTION, KEY_ID, KEY_RATE, KEY_POS, KEY_RECIP);
    
    /**
     * Table name
     */
    public static final String TB_ENTRY = "entry";
    
    /**
     * Creates Table entry
     */
    public static final String CREATE_TB_ENTRY = String.format(
            "CREATE TABLE %s (\n" +
            "    %s     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                   NOT NULL,\n" +        
            "    %s     INTEGER REFERENCES %s (%s)      ON DELETE RESTRICT\n" +
            "                                           ON UPDATE RESTRICT\n" +
            "                   NOT NULL,\n" +        
            "    %s     INTEGER REFERENCES %s (%s)      ON DELETE RESTRICT\n" +
            "                                           ON UPDATE RESTRICT\n" +
            "                   NOT NULL,\n" +        
            "    %s     INTEGER NOT NULL\n" +                        
            ")", TB_ENTRY, KEY_ID, KEY_TRN_ID, TB_TRANSACTION, KEY_ID, KEY_ACC_ID,
                       TB_ACCOUNT, KEY_ID, KEY_AMOUNT);
    
    /**
     * Table name
     */
    public static final String TB_BAL = "balance";
    
    public static final String CREATE_TB_BAL = String.format(
            "CREATE TABLE %s (\n" +
            "    %s    INTEGER REFERENCES %s (%s) ON DELETE CASCADE\n" +
"                                                 ON UPDATE CASCADE\n" +
            "                  NOT NULL,\n" +
            "    %s    INTEGER NOT NULL,\n" +
            "    %s    INTEGER NOT NULL,\n" +
            "    PRIMARY KEY (\n" +
            "        %s,\n" +
            "        %s\n" +
            "    )\n" +
            ");", TB_BAL, KEY_ID, TB_ACCOUNT, KEY_ID, KEY_DATE, KEY_BAL, KEY_ID, KEY_DATE);
    
    /**
     * Table name
     */
    public static final String TB_POCKET_CONFIG = "pocket_config";
    
    /**
     * Create table account_type
     */
    public static final String CREATE_TB_POCKET_CONFIG = String.format("CREATE TABLE %s (\n" +
                    "    %s               INTEGER PRIMARY KEY\n" +
                    "                             NOT NULL,\n" +
                    "    %s               INTEGER NOT NULL,\n" +
                    "    %s               INTEGER NOT NULL\n" +
                    "                             REFERENCES %s (%s)       ON DELETE RESTRICT\n" +
                    "                                                      ON UPDATE CASCADE,\n" +
                    "    %s               INTEGER NOT NULL\n" +
                    "                             REFERENCES %s (%s)       ON DELETE RESTRICT\n" +
                    "                                                      ON UPDATE CASCADE\n" +
                    ");", TB_POCKET_CONFIG, KEY_ID, KEY_VERSION, KEY_PRIM_CUR, TB_CURRENCY,
                                        KEY_ID, KEY_DEF_CAT, TB_CATEGORY, KEY_ID);
    
    /**
     * Constant Values of account_type to insert
     */
    public static final String CONST_INSERT_ACCOUNT_TYPES = String.format(
            "INSERT INTO %s (%s, %s)\n" +
            "VALUES (1, 'NORMAL'),\n" +            
            "       (2, 'CATEGORY'),\n" +
            "       (3, 'SUB_CATEGORY'),\n" +
            "       (4, 'PEOPLE')", TB_ACCOUNT_TYPE, KEY_ID, KEY_NAME);
    
    /**
     * Constant Values of account_type to insert
     */
    public static final String CONST_INSERT_CATEGORY_CLASS = String.format(
            "INSERT INTO %s (%s, %s)\n" +
            "VALUES (1, 'INCOME'),\n" +            
            "       (2, 'EXPENSE'),\n" +            
            "       (3, 'OTHER')", TB_CATEGORY_CLASS, KEY_ID, KEY_NAME);    
    
    /**
     * Constant Values of trans_type to insert
     */
    public static final String CONST_INSERT_TRANS_TYPES = String.format(
            "INSERT INTO %s (%s, %s)\n" +
            "VALUES (1, 'INCOME'),\n" +
            "       (2, 'EXPENSE'),\n" +
            "       (3, 'TRANSFER'),\n" +
            "       (4, 'TRANSFER_DELETED'),\n" +
            "       (5, 'LEND'),\n" +
            "       (6, 'BORROW')", TB_TRANSACTION_TYPE, KEY_ID, KEY_NAME);
    
    /**
     * Constant Values of currency to insert
     */
    public static final String CONST_INSERT_CURRENCY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)\n" +
            "VALUES  " +
            "        (840, 'U.S. Dollar', 1, 0, 2, 0, 'true', 'USD', '$', 1),\n" +
            "        (886, 'Yemeni Rials', 215, 0, 2, 0, 'true', 'YER', '', 0)",
            TB_CURRENCY, KEY_ID, KEY_NAME, KEY_RATE, KEY_RECIP, KEY_DECIMAL, KEY_POS,
            KEY_USED, KEY_SYM, KEY_ABBR, KEY_HAS_ABBR);
    
    
    
    /**
     * Array of strings contain all required statements to create the database,
     * including default values
     */
    public static final String[] CREATE_DB = {
        CREATE_TB_ACCOUNT_TYPE,
        CREATE_TB_ACCOUNT,
        CREATE_TB_ACCOUNT_GROUP,
        CREATE_TB_CURRENCY,
        CREATE_TB_NORMAL_ACCOUNT,
        CREATE_TB_PEOPLE_GROUP,
        CREATE_TB_PEOPLE_ACCOUNT,
        CREATE_TB_CATEGORY_CLASS,
        CREATE_TB_CATEGORY,
        CREATE_TB_SUB_CATEGORY,
        CREATE_TB_PAYEE,
        CREATE_TB_TRANSACTION_TYPE,
        CREATE_TB_TRANSACTION,
        CREATE_TB_TRANSACTION_FX,
        CREATE_TB_ENTRY,        
        CREATE_TB_BAL,
        CREATE_TB_POCKET_CONFIG,
        CONST_INSERT_ACCOUNT_TYPES,
        CONST_INSERT_CATEGORY_CLASS,
        CONST_INSERT_TRANS_TYPES,
        CONST_INSERT_CURRENCY
    };
    
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="Exit Checking Statements">
    /**
     * Template SQL Statement
     */
    private static final String EXISTS_TEMPLATE = 
            "SELECT %s FROM %s\n" +
             "WHERE %s = ?";
    
    /**
     * Read from table account
     */
    public static final String EXISTS_ACCOUNT = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_ACCOUNT, KEY_ID);
    
    /**
     * Read from table normal_account
     */
    public static final String EXISTS_NORMAL = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_NORMAL_ACCOUNT, KEY_ID);
    
    /**
     * Read from table account_group
     */
    public static final String EXISTS_ACCOUNT_GROUP = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_ACCOUNT_GROUP, KEY_ID);
    
    /**
     * Read from table people_account
     */
    public static final String EXISTS_PEOPLE = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_PEOPLE_ACCOUNT, KEY_ID);
    
    /**
     * Read from table people_group
     */
    public static final String EXISTS_PEOPLE_GROUP = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_PEOPLE_GROUP, KEY_ID);
    
    /**
     * Read from table currency
     */
    public static final String EXISTS_CURRENCY = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_CURRENCY, KEY_ID);
    
    /**
     * Read from table category
     */
    public static final String EXISTS_CATEGORY = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_CATEGORY, KEY_ID);
    
    /**
     * Read from table sub_category
     */
    public static final String EXISTS_SUB_CATEGORY = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_SUB_CATEGORY, KEY_ID);
    
    /**
     * Read from table trans
     */
    public static final String EXISTS_TRANSACTION = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_TRANSACTION, KEY_ID);
    
    /**
     * Read from table entry
     */
    public static final String EXISTS_ENTRY = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_ENTRY, KEY_ID);
    
    /**
     * Read from table balance
     */
    public static final String EXISTS_BALANCE = String.format(
            EXISTS_TEMPLATE + "\n" + 
            "AND %s = ?", KEY_ID, TB_BAL, KEY_ID, KEY_DATE);
    
    /**
     * Read from table payee
     */
    public static final String EXISTS_PAYEE = String.format(
            EXISTS_TEMPLATE, KEY_ID, TB_PAYEE, KEY_ID);
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="INSERT Statements">    
    /**
     * Insert to table account
     */
    public static final String INSERT_ACCOUNT = String.format(
            "INSERT INTO %s (%s, %s, %s)\n" +
            "VALUES (?, ?, ?)", TB_ACCOUNT, KEY_NAME, KEY_TYPE, KEY_BAL);
    
    /**
     * Insert to table normal_account
     */
    public static final String INSERT_NORMAL = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s)\n" +
            "VALUES (?, ?, ?, ?, ?)", 
            TB_NORMAL_ACCOUNT, KEY_ID, KEY_OPEN_BAL, KEY_CLOSED, KEY_CURR_ID, KEY_GRP_ID);
    
    /**
     * Insert to table account_group
     */
    public static final String INSERT_ACCOUNT_GROUP = String.format(
            "INSERT INTO %s (%s)\n" +
            "VALUES (?)", 
            TB_ACCOUNT_GROUP, KEY_NAME);
    
    /**
     * Insert to table people_account
     */
    public static final String INSERT_PEOPLE = String.format(
            "INSERT INTO %s (%s, %s, %s, %s)\n" +
            "VALUES (?, ?, ?, ?)", TB_PEOPLE_GROUP, KEY_ID, KEY_OPEN_BAL, KEY_CURR_ID, KEY_GRP_ID);
    
    /**
     * Insert to table people_group
     */
    public static final String INSERT_PEOPLE_GROUP = String.format(
            "INSERT INTO %s (%s)\n" +
            "VALUES (?)", 
            TB_PEOPLE_GROUP, KEY_NAME);
    
    /**
     * Insert to table category
     */
    public static final String INSERT_CATEGORY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s)\n" +
            "VALUES (?, ?, ?, ?)", TB_CATEGORY, KEY_ID, KEY_CLASS, KEY_BUDGET, KEY_HAS_BUDGET);
        
    /**
     * Insert to table sub_category
     */
    public static final String INSERT_SUB_CATEGORY = String.format(
            "INSERT INTO %s (%s, %s)\n" +
            "VALUES (?, ?)", TB_SUB_CATEGORY, KEY_ID, KEY_CAT_ID);
    
    /**
     * Insert to table trans
     */
    public static final String INSERT_TRANSACTION = String.format(
            "INSERT INTO %s (%s, %s, %s, %s)\n" +
            "VALUES (?, ?, ?, ?)", TB_TRANSACTION, KEY_DATE, KEY_COMMENT, KEY_PAYEE, KEY_TYPE);
    
    /**
     * Insert to table trans_fx
     */
    public static final String INSERT_TRANSACTION_FX = String.format(
            "INSERT INTO %s (%s, %s, %s, %s)\n" +
            "VALUES (?, ?, ?, ?)", TB_TRANSACTION_FX, KEY_ID, KEY_RATE, KEY_POS, KEY_RECIP);
    
    /**
     * Insert to table entry
     */
    public static final String INSERT_ENTRY = String.format(
            "INSERT INTO %s (%s, %s, %s)\n" +
            "VALUES (?, ?, ?)", TB_ENTRY, KEY_TRN_ID, KEY_ACC_ID, KEY_AMOUNT);
    
    /**
     * Insert or Update to table entry
     */
    public static final String INSERT_BALANCE = String.format(
            "INSERT INTO %s (%s, %s, %s)\n" +
            "VALUES (?, ?, ?)", TB_BAL, KEY_ID, KEY_DATE, KEY_BAL);
        
    /**
     * Insert to table payee
     */
    public static final String INSERT_PAYEE = String.format(
            "INSERT INTO %s (%s)\n" +
            "VALUES (?)", TB_PAYEE, KEY_NAME);
    
    /**
     * Insert to table pocket_config
     */
    public static final String INSERT_POCKET_CONFIG = String.format("INSERT INTO %s (%s, %s, %s, %s)\n" +
            "VALUES (1, ?, ?, ?)", TB_POCKET_CONFIG, KEY_ID, KEY_VERSION, KEY_PRIM_CUR, KEY_DEF_CAT);
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="UPDATE Statements">
    /**
     * Update in table account
     */
    public static final String UPDATE_ACCOUNT = String.format(
            "UPDATE %s\n" +
            "SET %s = ?\n" +
            "WHERE %s = ?", TB_ACCOUNT, KEY_NAME, KEY_ID);    
    
    /**
     * Update in table account
     */
    public static final String UPDATE_ACCOUNT_BAL = String.format(
            "UPDATE %s\n" +
            "SET %s = %s + ?\n" +
            "WHERE %s = ?", TB_ACCOUNT, KEY_BAL, KEY_BAL, KEY_ID);    
    
    /**
     * Update in table normal_account
     */
    public static final String UPDATE_NORMAL_ACCOUNT = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +
            "    %s = ?,\n" +
            "    %s = ?\n" +
            "WHERE %s = ?", TB_NORMAL_ACCOUNT, KEY_OPEN_BAL, KEY_CURR_ID,
            KEY_GRP_ID, KEY_ID);
    
    /**
     * Update in table normal_account
     */
    public static final String UPDATE_NORMAL_ACCOUNT_CLOSED = String.format(
            "UPDATE %s\n" +
            "SET %s = ?\n" +
            "WHERE %s = ?", TB_NORMAL_ACCOUNT, KEY_CLOSED, KEY_ID);
    
    /**
     * Update in table account_group
     */
    public static final String UPDATE_ACCOUNT_GROUP = String.format(
            "UPDATE %s\n" +
            "SET %s = ?\n" +
            "WHERE %s = ?", TB_ACCOUNT_GROUP, KEY_NAME, KEY_ID);
    
    /**
     * Update in table people_account
     */
    public static final String UPDATE_PEOPLE_ACCOUNT = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +
            "    %s = ?,\n" +
            "    %s = ?\n" +
            "WHERE %s = ?", TB_PEOPLE_ACCOUNT, KEY_OPEN_BAL, KEY_CURR_ID,
            KEY_GRP_ID, KEY_ID);
    
    /**
     * Update in table people_group
     */
    public static final String UPDATE_PEOPLE_GROUP = String.format(
            "UPDATE %s\n" +
            "SET %s = ?\n" +
            "WHERE %s = ?", TB_PEOPLE_GROUP, KEY_NAME, KEY_ID);
    
    /**
     * Update in table category
     */
    public static final String UPDATE_CATEGORY = String.format(
            "UPDATE %s\n" +
            "SET %s = ?\n" +
            "WHERE %s = ?", TB_CATEGORY, KEY_CLASS, KEY_ID);
    
    /**
     * Update in table category
     */
    public static final String UPDATE_CATEGORY_BUDGET = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +
            "    %s = 'true'\n" +
            "WHERE %s = ?", TB_CATEGORY, KEY_BUDGET, KEY_HAS_BUDGET, KEY_ID);
    
    /**
     * Update in table category
     */
    public static final String UPDATE_CATEGORY_NO_BUDGET = String.format(
            "UPDATE %s\n" +
            "SET %s = 0,\n" +
            "    %s = 'false'\n" +
            "WHERE %s = ?", TB_CATEGORY, KEY_BUDGET, KEY_HAS_BUDGET, KEY_ID);
    
    /**
     * Update in table sub_category
     */
    public static final String UPDATE_SUB_CATEGORY = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +            
            "WHERE %s = ?", TB_SUB_CATEGORY, KEY_CAT_ID, KEY_ID);
    
    /**
     * Update in table balance
     */
    public static final String UPDATE_BALANCE = String.format(
            "UPDATE %s\n" +
            "SET %s = %s + ?\n" +            
            "WHERE %s = ? AND %s = ?", TB_BAL, KEY_BAL, KEY_BAL, KEY_ID, KEY_DATE);
    
    /**
     * Update in table balance
     */
    public static final String UPDATE_BALANCE_ALL = String.format(
            "UPDATE %s\n" +
            "SET %s = %s + ?\n" +            
            "WHERE %s = ?", TB_BAL, KEY_BAL, KEY_BAL, KEY_ID);
    
    /**
     * Update in table balance
     */
    public static final String UPDATE_BALANCE_FUTURE = String.format(
            "UPDATE %s\n" +
            "SET %s = %s + ?\n" +            
            "WHERE %s = ? AND %s > ?", TB_BAL, KEY_BAL, KEY_BAL, KEY_ID, KEY_DATE);
    
    /**
     * Update in table trans
     */
    public static final String UPDATE_TRANSACTION = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +
            "    %s = ?,\n" +
            "    %s = ?,\n" +
            "    %s = ?\n" +
            "WHERE %s = ?", TB_TRANSACTION, KEY_DATE, KEY_COMMENT, KEY_PAYEE, KEY_TYPE, KEY_ID);
    
    /**
     * Update in table entry
     */
    public static final String UPDATE_ENTRY = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +
            "    %s = ?,\n" +
            "    %s = ? \n" +
            "WHERE %s = ?", TB_ENTRY, KEY_TRN_ID, KEY_ACC_ID, KEY_AMOUNT, KEY_ID);
    
    /**
     * Update in table pocket_config
     */
    public static final String UPDATE_POCKET_CONFIG_PRIM_CUR = String.format(
            "UPDATE %s\n" +
            "SET %s = ?,\n" +            
            "WHERE %s = 1", TB_POCKET_CONFIG, KEY_PRIM_CUR, KEY_ID);
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="SELECT Statements">    
    /**
     * Select from table account
     */
    public static final String SELECT_ACCOUNT_BALANCE = String.format(
            "SELECT %s FROM %s\n" +
            "WHERE %s = ?;", KEY_BAL, TB_ACCOUNT, KEY_ID);
    
    /**
     * Select all from table account Joined with normal_account
     */
    public static final String SELECT_NORMAL_ACCOUNT_ALL = String.format(
            "SELECT %s.%s, %s, %s, %s, %s, %s, %s\n" +
            "FROM %s JOIN %s \n" +
            "ON %s.%s = %s.%s\n", TB_ACCOUNT, KEY_ID, KEY_NAME, KEY_BAL, KEY_OPEN_BAL, KEY_CLOSED,
            KEY_CURR_ID, KEY_GRP_ID, TB_ACCOUNT, TB_NORMAL_ACCOUNT, TB_ACCOUNT,
            KEY_ID, TB_NORMAL_ACCOUNT, KEY_ID);
    
    /**
     * Select from table account Joined with normal_account
     */
    public static final String SELECT_NORMAL_ACCOUNT = String.format(SELECT_NORMAL_ACCOUNT_ALL + "\n" +            
            "WHERE %s.%s = ?", TB_ACCOUNT, KEY_ID);
    
    /**
     * Select from table normal_account
     */
    public static final String SELECT_NORMAL_ACCOUNT_CLOSED = String.format(
            "SELECT %s FROM %s " +
            "WHERE %s = ?", KEY_CLOSED, TB_NORMAL_ACCOUNT, KEY_ID);
    
    /**
     * Select from table normal_account
     */
    public static final String SELECT_NORMAL_ACCOUNT_OPEN_BAL = String.format(
        "SELECT %s FROM %s " +
        "WHERE %s = ?", KEY_OPEN_BAL, TB_NORMAL_ACCOUNT, KEY_ID);
    
    /**
     * Select all from table account Joined with people_account
     */
    public static final String SELECT_PEOPLE_ACCOUNT_ALL = String.format(
            "SELECT %s.%s, %s, %s, %s, %s, %s\n" +
            "FROM %s JOIN %s \n" +
            "ON %s.%s = %s.%s\n", TB_ACCOUNT, KEY_ID, KEY_NAME, KEY_BAL, KEY_OPEN_BAL, 
            KEY_CURR_ID, KEY_GRP_ID, TB_ACCOUNT, TB_PEOPLE_ACCOUNT, TB_ACCOUNT,
            KEY_ID, TB_NORMAL_ACCOUNT, KEY_ID);
    
    /**
     * Select from table account Joined with people_account
     */
    public static final String SELECT_PEOPLE_ACCOUNT = String.format(SELECT_PEOPLE_ACCOUNT_ALL + "\n" +            
            "WHERE %s.%s = ?", TB_ACCOUNT, KEY_ID);
    
    /**
     * Select from table people_account
     */
    public static final String SELECT_PEOPLE_ACCOUNT_OPEN_BAL = String.format(
        "SELECT %s FROM %s " +
        "WHERE %s = ?", KEY_OPEN_BAL, TB_PEOPLE_ACCOUNT, KEY_ID);
    
    /**
     * Select from table currency
     */
    public static final String SELECT_CURRENCY_USED = String.format(
            "SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n" +
            "FROM %s\n" +
            "WHERE %s = 'true'", KEY_ID, KEY_NAME, KEY_RATE, KEY_RECIP, KEY_DECIMAL,
                        KEY_POS, KEY_USED, KEY_SYM, KEY_ABBR, KEY_HAS_ABBR, TB_CURRENCY, KEY_USED);
    
    /**
     * Select from table sub_category
     */
    public static final String SELECT_SUB_CATEGORY_LINKED = String.format(
            "SELECT %s FROM %s\n" +
            "WHERE %s = ?", KEY_ID, TB_SUB_CATEGORY, KEY_CAT_ID);
    
    /**
     * Select from table balance
     */
    public static final String SELECT_BALANCE_PREVIOUS = String.format(
            "SELECT %s FROM %s\n" +
            "WHERE %s = ? AND %s < ?", KEY_BAL, TB_BAL, KEY_ID, KEY_DATE);
    
    /**
     * Select from table currency
     */
    public static final String SELECT_CURRENCY = String.format(
            "SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n" +
            "FROM %s\n" +
            "WHERE %s = ?", KEY_ID, KEY_NAME, KEY_RATE, KEY_RECIP, KEY_DECIMAL,
                        KEY_POS, KEY_USED, KEY_SYM, KEY_ABBR, KEY_HAS_ABBR, TB_CURRENCY, KEY_ID);
    
    /**
     * Select from table trans left outer join trans_fx
     */
    public static final String SELECT_TRANSACTION = String.format(
            "SELECT a.%s, a.%s, a.%s, a.%s, a.%s, b.%s, b.%s, b.%s\n" +
            "FROM %s a LEFT OUTER JOIN %s b\n" +
            "ON a.%s = b.%s " +
            "WHERE a.%s = ?", KEY_ID, KEY_DATE, KEY_COMMENT, KEY_PAYEE, KEY_TYPE,
                            KEY_RATE, KEY_POS, KEY_RECIP, TB_TRANSACTION, TB_TRANSACTION_FX,
                            KEY_ID, KEY_ID, KEY_ID);
    
    /**
     * Select from table entry
     */
    public static final String SELECT_ENTRY_TRANSACTION = String.format(
            "SELECT a.%s, a.%s, a.%s, b.%s\n" +
            "FROM %s a JOIN %s b\n" +
            "ON a.%s = b.%s\n" +
            "WHERE a.%s = ?", KEY_ID, KEY_ACC_ID, KEY_AMOUNT, KEY_TYPE, TB_ENTRY,
                        TB_ACCOUNT, KEY_ACC_ID, KEY_ID, KEY_TRN_ID);
    
    /**
     * Select from table payee
     */
    public static final String SELECT_PAYEE = String.format(
            "SELECT %s, %s\n" +
            "FROM %s\n" +
            "WHERE %s = ?", KEY_ID, KEY_NAME, TB_PAYEE, KEY_ID);
    
    /**
     * Select from table pocket
     */
    public static final String SELECT_POCKET_CONFIG = String.format("SELECT %s, %s, %s\n" +
            "FROM %s\n" +
            "WHERE %s = ?", KEY_VERSION, KEY_PRIM_CUR, KEY_DEF_CAT, TB_POCKET_CONFIG, KEY_ID);
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DELETE Statements">
    /**
     * Delete from table account_group
     */
    public static final String DELETE_ACCOUNT_GROUP = String.format(
            "DELETE FROM %s\n" +            
            "WHERE %s = ?", TB_ACCOUNT_GROUP, KEY_ID);  
    
    /**
     * Delete from table account_group
     */
    public static final String DELETE_ENTRY = String.format(
            "DELETE FROM %s\n" +            
            "WHERE %s = ?", TB_ENTRY, KEY_ID);  
    
    /**
     * Delete from table trans_fx
     */
    public static final String DELETE_TRANS_FX = String.format(
            "DELETE FROM %s\n" +            
            "WHERE %s = ?", TB_TRANSACTION_FX, KEY_ID);  
    
    // </editor-fold>
}
