/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.db;

/**
 * Utility class to retrieve SQL Statements
 * @author Aymen Alquaiti
 * Date: 02/02/2016
 */
public class SQLMaker
{
    /**
     * Create table account_type
     */
    public static final String CREATE_TB_ACCOUNT_TYPE = 
                    "CREATE TABLE account_type (\n" +
                    "    id   INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                    "    name TEXT (255) NOT NULL\n" +
                    ")";
    
    /**
     * Creates Table account
     */
    public static final String CREATE_TB_ACCOUNT =
            "CREATE TABLE account (\n" +
            "    id      INTEGER    PRIMARY KEY AUTOINCREMENT,\n" +
            "    name    TEXT (255) NOT NULL,\n" +
            "    type               REFERENCES account_type (id) ON DELETE RESTRICT\n" +
            "                       NOT NULL,\n" +
            "    balance INTEGER    NOT NULL\n" +
            ")";
    
    /**
     * Creates TAble account_group
     */
    public static final String CREATE_TB_ACCOUNT_GROUP =
            "CREATE TABLE account_group (\n" +
            "    id   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    name TEXT    NOT NULL\n" +
            ")";
    
    /**
     * Creates Table currency
     */
    public static final String CREATE_TB_CURRENCY =
            "CREATE TABLE currency (\n" +
            "    id              INTEGER    PRIMARY KEY AUTOINCREMENT,\n" +
            "    name            TEXT (255) NOT NULL,\n" +
            "    rate            INTEGER    NOT NULL,\n" +
            "    reciprocal      BOOLEAN    NOT NULL,\n" +
            "    decimal         INTEGER    NOT NULL,\n" +
            "    pos             INTEGER    NOT NULL,\n" +
            "    used            BOOLEAN,\n" +
            "    symbol          TEXT (255) NOT NULL,\n" +
            "    abbreviation    TEXT (255) NOT NULL,\n" +
            "    hasAbbreviation BOOLEAN    NOT NULL\n" +
            ")";
    
    /**
     * Creates Table normal_account
     */
    public static final String CREATE_TB_NORMAL_ACCOUNT =
            "CREATE TABLE normal_account (\n" +
            "    id          INTEGER REFERENCES account (id) ON DELETE CASCADE\n" +
            "                                                ON UPDATE CASCADE,\n" +
            "    openBalance INTEGER NOT NULL,\n" +
            "    closed      BOOLEAN NOT NULL,\n" +
            "    curr_id     INTEGER REFERENCES currency (id) ON DELETE RESTRICT\n" +
            "                                                 ON UPDATE CASCADE,\n" +
            "    grp_id      INTEGER REFERENCES account_group (id) ON DELETE SET NULL\n" +
            "                                                      ON UPDATE CASCADE\n" +
            ")";
    
    /**
     * Creates Table category
     */
    public static final String CREATE_TB_CATEGORY =
            "CREATE TABLE category (\n" +
            "    id         INTEGER REFERENCES account (id) ON DELETE CASCADE\n" +
            "                                               ON UPDATE CASCADE,\n" +
            "    budget     INTEGER NOT NULL,\n" +
            "    has_budget BOOLEAN NOT NULL\n" +
            ")";
    
    /**
     * Creates Table sub_category
     */
    public static final String CREATE_TB_SUB_CATEGORY =
            "CREATE TABLE sub_category (\n" +
            "    id     INTEGER REFERENCES account (id) ON DELETE CASCADE\n" +
            "                                           ON UPDATE CASCADE,\n" +
            "    cat_id INTEGER REFERENCES category (id) ON DELETE CASCADE\n" +
            "                                            ON UPDATE CASCADE\n" +
            ")";
    
    public static final String CREATE_TB_TRANSACTION =
            "CREATE TABLE trans (\n" +
            "    id      INTEGER    PRIMARY KEY AUTOINCREMENT,\n" +
            "    date    INTEGER    NOT NULL,\n" +
            "    comment TEXT (500) NOT NULL\n" +
            ")";
    
    /**
     * Creates Table entry
     */
    public static final String CREATE_TB_ENTRY =
            "CREATE TABLE entry (\n" +
            "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    trn_id INTEGER REFERENCES trans (id) ON DELETE RESTRICT\n" +
            "                                         ON UPDATE RESTRICT,\n" +
            "    acc_id INTEGER REFERENCES account (id) ON DELETE RESTRICT\n" +
            "                                           ON UPDATE RESTRICT,\n" +
            "    amount INTEGER NOT NULL\n" +
            ")";
    
    /**
     * Array of strings contain all required statements to create the database
     */
    public static final String[] CREATE_DB = {
        CREATE_TB_ACCOUNT_TYPE,
        CREATE_TB_ACCOUNT,
        CREATE_TB_ACCOUNT_GROUP,
        CREATE_TB_CURRENCY,
        CREATE_TB_NORMAL_ACCOUNT,
        CREATE_TB_CATEGORY,
        CREATE_TB_SUB_CATEGORY,
        CREATE_TB_TRANSACTION,
        CREATE_TB_ENTRY
    };
}
