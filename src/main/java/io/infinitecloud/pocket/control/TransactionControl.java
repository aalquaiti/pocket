/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.control;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.infinitecloud.pocket.PocketFactory;
import io.infinitecloud.pocket.entity.AccountType;
import io.infinitecloud.pocket.entity.Currency;
import io.infinitecloud.pocket.entity.Entry;
import io.infinitecloud.pocket.entity.History;
import io.infinitecloud.pocket.entity.PocketConfig;
import io.infinitecloud.pocket.entity.Transaction;
import io.infinitecloud.pocket.entity.TransactionType;
import io.infinitecloud.pocket.exception.AccountException;
import io.infinitecloud.pocket.exception.CategoryException;
import io.infinitecloud.pocket.exception.CurrencyException;
import io.infinitecloud.pocket.exception.PayeeException;
import io.infinitecloud.pocket.exception.PeopleAccountException;
import io.infinitecloud.pocket.exception.SubCategoryException;
import io.infinitecloud.pocket.exception.TransactionException;
import io.infinitecloud.pocket.mapper.AccountMapper;
import io.infinitecloud.pocket.mapper.BalanceMapper;
import io.infinitecloud.pocket.mapper.CategoryMapper;
import io.infinitecloud.pocket.mapper.CurrencyMapper;
import io.infinitecloud.pocket.mapper.EntryMapper;
import io.infinitecloud.pocket.mapper.NormalAccountMapper;
import io.infinitecloud.pocket.mapper.PayeeMapper;
import io.infinitecloud.pocket.mapper.PeopleAccountMapper;
import io.infinitecloud.pocket.mapper.SubCategoryMapper;
import io.infinitecloud.pocket.mapper.TransactionMapper;
import io.infinitecloud.pocket.util.ConnectionManager;
import io.infinitecloud.pocket.util.DateTimeUtil;
import io.infinitecloud.pocket.util.FXUtil;

/**
 * Provides Manipulation methods for all functions related to transaction and
 * entries
 * @author Aymen Alquaiti
 * <p>Date: 01/02/2016</p>
 */
public class TransactionControl
{        
    private final ConnectionManager cm;
    private final AccountMapper account;
    private final NormalAccountMapper normal;
    private final PeopleAccountMapper people;
    private final CategoryMapper cat;
    private final SubCategoryMapper sub;
    private final CurrencyMapper cur;
    private final TransactionMapper trans;
    private final EntryMapper entry;
    private final PayeeMapper payee;
    private final BalanceMapper bal;    
    private final PocketConfig config;
    
    // Used for reversal
    private Transaction oldTrn;
    private Entry oldAcc;
    private Entry oldCat;
    private List<Entry> oldSubs;
    private Entry oldFrom;
    private Entry oldTo;

    public TransactionControl(ConnectionManager cm, PocketConfig config, AccountMapper account, 
            NormalAccountMapper normal, PeopleAccountMapper people, CategoryMapper cat,
            SubCategoryMapper sub, CurrencyMapper cur, TransactionMapper trans,
            EntryMapper entity, PayeeMapper payee ,BalanceMapper bal)
    {
        this.cm = cm;
        this.account = account;
        this.normal = normal;
        this.people = people;
        this.cat = cat;
        this.sub = sub;
        this.cur = cur;
        this.trans = trans;
        this.entry = entity;
        this.payee = payee;
        this.bal = bal;
        this.config = config;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods"> 
    
    // <editor-fold defaultstate="collapsed" desc="Transaction Methods"> 
    
    /**
     * Retrieve Transaction Entity
     * @param id Transaction id
     * @return Transaction Entity
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     */
    public Transaction get(long id) throws TransactionException
    {
        cm.open();
        Transaction result = getTransaction(id);
        cm.close();
        
        return result;
    }
    
    /**
     * Deletes a transaction
     * @param trnId Transaction id
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     */
    public void deleteTransaction(long trnId) throws TransactionException
    {
        
    }    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="History Methods"> 
    /**
     * Retrieve History of an account. Number of entries to retrieve, 
     * or date (from - to) is dependent on Pocket system configuration
     * @param account Account id
     * @return History Object.
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public History getHistory(long account) throws AccountException
    {
        return null;
    }
    
    /**
     * Retrieve History of an account. Number of entries returned is as specified.
     * Entries are returned according to their date, the newer the higher the priority.
     * This method will return future entries.
     * @param account Account id
     * @param entries Number of entries to return
     * @return History Object
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public History getHistory(long account, int entries) throws AccountException
    {
        return null;
    }
    
    /**
     * Retrieve History of an account. Retrieve entries from a specified date.
     * Entries will be returned only for that specified date.
     * @param date entries date
     * @return History Object
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public History getHistory(Date date) throws AccountException
    {
        return null;
    }
    
    /**
     * Retrieve History of an account. Retrieve entries from a specified date to 
     * a specified date.
     * @param account Account id
     * @param from From date
     * @param to To date
     * @return History object
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     * @throws IllegalArgumentException if (to) date is less than (from) date
     */
    public History getHistory(long account, Date from, Date to) throws AccountException
    {
        return null;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Income Methods"> 
    
    /**
     * Add Income transaction to account and category. The amount will be registered as positive
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. Amount will be registered as positive, even
     * if provided in negative number
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws PayeeException with state {@link PayeeException#NOT_EXISTS} if
     * payee does not exists with specified id
     */
    public long addIncome(long accId, long catId, Long payId, Date date, String comment,
            long amount) throws AccountException, CategoryException, PayeeException
    {
        return addIncome(accId, catId, payId, date, comment, amount, null, null);        
    }
    
    /**
     * Add Income transaction to account, category and sub categories. 
     * The amount will be registered as positive
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. Amount will be registered as positive, even
     * if provided in negative number
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws SubCategoryException with state:
     * <p>
     * 1. {@link SubCategoryException#NOT_EXISTS} if one of the sub categories 
     * provided does not exists
     * </p>
     * <p>
     * 2. {@link SubCategoryException#NOT_LINKED} if one of the sub categories
     * is not linked with provided category
     * </p>
     * <p>
     * 3. {@link SubCategoryException#NOT_EQUAL} if sum of array of sub category
     * amount is not equal to transaction amount
     * </p>
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     * @throws PayeeException with state {@link PayeeException#NOT_EXISTS} if
     * payee does not exists with specified id
     */
    public long addIncome(long accId, long catId, Long payId, Date date, String comment,
            long amount, long subId[], long subAmount[]) throws AccountException,
                CategoryException, PayeeException, SubCategoryException, IllegalArgumentException
    {
        cm.open();
        long result = addIncomeExpense(accId, catId, payId, date, comment, amount, subId, subAmount, true);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit transaction as Income. If transaction is not an income transaction,
     * it will change accordingly. If transaction of type income, but has sub 
     * categories, they will be removed. For sub categories, use 
     * {@link #editIncome(long, long, long, java.sql.Date, java.lang.String, long, long[], long[]) }
     * instead.
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public void editIncome(long trnId, long accId, long catId, Long payId, Date date,
            String comment, long amount) 
                throws TransactionException, AccountException, CategoryException
    {
        editIncome(trnId, accId, catId, payId, date, comment, amount, null, null);
    }
    
    /**
     * Edit transaction as Income. If transaction is not an income transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws SubCategoryException with state:
     * <p>
     * 1. {@link SubCategoryException#NOT_EXISTS} if one of the sub categories 
     * provided does not exists
     * </p>
     * <p>
     * 2. {@link SubCategoryException#NOT_LINKED} if one of the sub categories
     * is not linked with provided category
     * </p>
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     */
    public void editIncome(long trnId, long accId, long catId, Long payId, Date date,
            String comment, long amount, long subId[], long subAmount[]) 
            throws TransactionException, AccountException, CategoryException,
                        SubCategoryException, IllegalArgumentException
    {
        editIncomeExpense(trnId, accId, catId, payId, date, comment, amount, subId, subAmount, true);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Expense Methods"> 
    
    /**
     * Add Expense transaction to account and category. The amount will be registered
     * as negative
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. Amount will be registered as negative, even
     * if provided in positive number
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public long addExpense(long accId, long catId, Long payId, Date date, String comment,
            long amount) throws AccountException, CategoryException
    {        
        return addExpense(accId, catId, payId, date, comment, amount, null, null);
    }
    
    /**
     * Add Expense transaction to account, category and sub categories. 
     * The amount will be registered as negative
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. Amount will be registered as negative, even
     * if provided in negative number
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     */
    public long addExpense(long accId, long catId, Long payId, Date date, String comment,
            long amount, long subId[], long subAmount[]) throws AccountException,
                CategoryException, IllegalArgumentException
    {
        cm.open();
        long result = addIncomeExpense(accId, catId, payId, date, comment, amount,
                subId, subAmount, false);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit transaction as Expense. If transaction is not an expense transaction,
     * it will change accordingly. If transaction of type expense, but has sub 
     * categories, they will be removed. Use 
     * {@link #editExpense(long, long, long, java.sql.Date, java.lang.String, long, long[], long[]) }
     * instead.
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public void editExpense(long trnId, long accId, long catId, Long payId, Date date,
            String comment, long amount) throws TransactionException,
                                        AccountException, CategoryException
    {
        editExpense(trnId, accId, catId, payId, date, comment, 0, null, null);
    }
    
    /**
     * Edit transaction as Expense. If transaction is not an expense transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws SubCategoryException with state:
     * <p>
     * 1. {@link SubCategoryException#NOT_EXISTS} if one of the sub categories 
     * provided does not exists
     * </p>
     * <p>
     * 2. {@link SubCategoryException#NOT_LINKED} if one of the sub categories
     * is not linked with provided category
     * </p>
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     */
    public void editExpense(long trnId, long accId, long catId, Long payId, Date date,
            String comment, long amount, long subId[], long subAmount[]) 
            throws TransactionException, AccountException, CategoryException, 
                    SubCategoryException, IllegalArgumentException
    {
        editIncomeExpense(trnId, accId, catId, payId, date, comment, amount, 
                subId, subAmount, false);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Transfer Methods"> 
    
    /**
     * Add Transfer transaction. From account will be registered as negative, while
     * to account will be registered as positive
     * @param fromId From Normal Account id
     * @param toId To Normal Account id     
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. From account will be registered as negative, while
     * to account will be registered as positive
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws CurrencyException with state {@link CurrencyException#NOT_SAME} if
     * from and to accounts' currency do not match
     */
    public long addTransfer(long fromId, long toId, Date date, String comment,
            long amount) throws AccountException, CurrencyException
    {
        cm.open();        
        normalExists(fromId);
        normalNotClosed(fromId);
        normalExists(toId);
        long result = addTransfer(fromId, toId, date, comment, amount, 
                TransactionType.TRANSFER, false, 0, 0, 0);
        cm.close();
        
        return result;
    }
    
    /**     
     * Edit transaction as Transfer. If transaction is not a transfer transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p> 
     */
    public void editTransfer(long trnId, long fromId, long toId, Date date, 
            String comment, long amount) throws TransactionException, AccountException
    {
        editTransfer(trnId, fromId, toId, date, comment, amount, TransactionType.TRANSFER);
    }
    
    /**
     * Add Transfer transaction between accounts of different currencies. 
     * From account will be registered as negative, while to account will
     * be registered as positive. If there
     * is any difference, it will recorded in default category
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction Date
     * @param comment Transaction comment
     * @param fromAmount From Amount
     * @param toAmount To Amount     
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public long addTransferFX(long fromId, long toId, long catId, Date date, String comment,
            long fromAmount, long toAmount) throws AccountException, CategoryException
    {
        cm.open();
        normalExists(fromId);
        normalNotClosed(fromId);
        normalExists(toId);
        normalNotClosed(toId);
        catExists(catId);
        long result = addTransferFX(fromId, toId, catId, date, comment, fromAmount, toAmount,
                        TransactionType.TRANSFER, false, 0, 0, 0);
        cm.close();
        
        return result;
    }    
    
    /**
     * Add Transfer transaction between accounts of different currencies. 
     * From account will be registered as negative, while to account will
     * be registered as positive. This method takes only one amount for (from 
     * account) or (to account). The other will be calculated by fx rate. If there
     * is any difference, it will recorded in default category
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount From or to Amount
     * @param fromAmount if true, the amount is for (From Account), else the amount
     * is for (to Account). The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public long addTransferFX(long fromId, long toId, long catId, Date date, String comment,
            long amount, boolean fromAmount,  long rate, int pos, boolean Reciprocal)
                                throws AccountException, CategoryException
    {
        cm.open();
        normalExists(fromId);
        normalNotClosed(fromId);
        normalExists(toId);
        normalNotClosed(toId);
        catExists(catId);
        
        long result = addTransferFX(fromId, toId, catId, date, comment, amount,
                fromAmount, rate, pos, Reciprocal, TransactionType.TRANSFER, false, 0, 0, 0);
        cm.close();
        
        return result;
    }    
    
    /**
     * Edit transaction as Transfer. If transaction is not a transfer transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction Date
     * @param comment Transaction comment
     * @param fromAmount From Amount
     * @param toAmount To Amount     
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public void editTransferFX(long trnId, long fromId, long toId, long catId, Date date,
            String comment, long fromAmount, long toAmount) 
                throws TransactionException, AccountException, CategoryException
    {
        editTransferFX(trnId, fromId, toId, catId, date, comment, fromAmount, 
                toAmount, TransactionType.TRANSFER);
    }
    
    /**
     * Edit transaction as Transfer. If transaction is not a transfer transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount From or to Amount
     * @param fromAmount if true, the amount is for (From Account), else the amount
     * is for (to Account). The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public void editTransferFX(long trnId, long fromId, long toId, long catId, Date date,
            String comment, long amount, boolean fromAmount,  long rate, int pos, boolean reciprocal)
                    throws TransactionException, AccountException, CategoryException
    {
        editTransferFX(trnId, fromId, toId, catId, date, comment, amount, fromAmount,
                rate, pos, reciprocal, TransactionType.TRANSFER);                
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Lend Methods"> 
    
    /**
     * Add Lend transaction. Normal account will be registered as negative, while
     * people account will be registered as positive
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction Amount. Normal account will be registered as negative, while
     * people account will be registered as positive
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public long addLend(long accId, long peopId, Date date, String comment,
            long amount) throws AccountException, PeopleAccountException
    {
        cm.open();
        normalExists(accId);
        normalNotClosed(accId);
        peopleExists(peopId);
        long result = addTransfer(accId, peopId, date, comment, amount, 
                TransactionType.LEND, false, 0, 0, 0);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit transaction as Lend. If transaction is not lend transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction Amount. Normal account will be registered as negative, while
     * people account will be registered as positive
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public void editLend(long trnId, long accId, long peopId, Date date, String comment,
            long amount) throws AccountException, PeopleAccountException
    {
        editTransfer(trnId, accId, peopId, date, comment, amount, TransactionType.LEND);
    }
    
    /**
     * Add Lend transaction between accounts of different currencies. 
     * Normal account will be registered as negative, while people account will
     * be registered as positive
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Normal account amount
     * @param accountAmount if true, the amount is from normal account, else the amount
     * is from people account. The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public long addLendFX(long accId, long peopId, long catId, Date date, String comment,
            long amount, boolean accountAmount,  long rate, int pos, boolean Reciprocal)
                                throws AccountException, PeopleAccountException
    {
        cm.open();
        long id;
        if(accountAmount)        
            id = peopId;
        else
            id = accId;
        
        long curId = normal.get(id).getCurrency().getId();
        Currency currency = getCurrency(curId);

        long otherAmount = FXUtil.makeFX(amount, currency.getDecimal(), rate, pos, !Reciprocal);        
        
        if(accountAmount)
        {
            long result = addTransferFX(accId, peopId, catId, date, comment, amount, otherAmount, 
                    TransactionType.LEND, rate, pos, Reciprocal, false, 0, 0, 0);
            cm.close();
            
            return result;
        }
        else
        {
            long result = addTransferFX(accId, peopId, catId, date, comment, otherAmount, amount, 
                    TransactionType.LEND, rate, pos, Reciprocal, false, 0, 0, 0);
            cm.close();
            
            return result;
        }
    }
    
    /**
     * Add Lend transaction between accounts of different currencies. 
     * Normal account will be registered as negative, while people account will
     * be registered as positive
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param accountAmount Normal account amount
     * @param peoplAmount People account amount          
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public long addLendFX(long accId, long peopId, long catId, Date date, String comment,
            long accountAmount, long peoplAmount) throws AccountException, PeopleAccountException
    {
        cm.open();
        normalExists(accId);
        normalNotClosed(accId);
        peopleExists(peopId);
        catExists(catId);
        long result = addTransferFX(accId, peopId, catId, date, comment, accountAmount,
                        peoplAmount, TransactionType.LEND, false, 0, 0, 0);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit transaction as Lend. If transaction is not lend transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param accountAmount Normal account amount
     * @param peoplAmount People account amount     
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public void editLendFX(long trnId, long accId, long peopId, Date date, String comment,
            long accountAmount, long peoplAmount,  long rate, long pos, boolean Reciprocal)
                                throws AccountException, PeopleAccountException
    {
        
    }
    
    /**
     * Edit transaction as Lend. If transaction is not lend transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Normal account amount
     * @param accountAmount if true, the amount is from normal account, else the amount
     * is from people account. The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public void editLendFX(long trnId, long accId, long peopId, Date date, String comment,
            long amount, boolean accountAmount,  long rate, long pos, boolean Reciprocal)
                                throws AccountException, PeopleAccountException
    {
        
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Borrow Methods"> 
    
    /**
     * Add Borrow transaction. Normal account will be registered as positive, while
     * people account will be registered as negative
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction Amount. Normal account will be registered as negative, while
     * people account will be registered as positive
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public long addBorrow(long accId, long peopId, Date date, String comment,
            long amount) throws AccountException, PeopleAccountException
    {
        cm.open();
        normalExists(accId);
        normalNotClosed(accId);
        peopleExists(peopId);
        long result = addTransfer(peopId, accId, date, comment, amount, 
                TransactionType.BORROW, false, 0, 0, 0);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit transaction as Borrow. If transaction is not borrow transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction Amount. Normal account will be registered as negative, while
     * people account will be registered as positive
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public void editBorrow(long trnId, long accId, long peopId, Date date, String comment,
            long amount) throws AccountException, PeopleAccountException
    {
        editTransfer(trnId, peopId, trnId, date, comment, amount, TransactionType.BORROW);
    }
    
    /**
     * Add Borrow transaction between accounts of different currencies. 
     * Normal account will be registered as positive, while people account will
     * be registered as negative
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Normal account amount
     * @param accountAmount if true, the amount is from normal account, else the amount
     * is from people account. The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public long addBorrowFX(long accId, long peopId, long catId, Date date, String comment,
            long amount, boolean accountAmount,  long rate, int pos, boolean Reciprocal)
                                throws AccountException, PeopleAccountException
    {
        cm.open();
        long id;
        if(accountAmount)        
            id = peopId;
        else
            id = accId;
        
        long curId = normal.get(id).getCurrency().getId();
        Currency currency = getCurrency(curId);

        long otherAmount = FXUtil.makeFX(amount, currency.getDecimal(), rate, pos, !Reciprocal);        
        
        if(accountAmount)
        {
            long result = addTransferFX(accId, peopId, catId, date, comment, amount, otherAmount, 
                            TransactionType.BORROW, rate, pos, Reciprocal, false, 0, 0, 0);
            cm.close();
            
            return result;
        }
        else
        {
            long result = addTransferFX(accId, peopId, catId, date, comment, otherAmount, amount, 
                            TransactionType.BORROW, rate, pos, Reciprocal, false, 0, 0, 0);
            cm.close();
            
            return result;
        }
    }
    
    /**
     * Add Borrow transaction between accounts of different currencies. 
     * Normal account will be registered as positive, while people account will
     * be registered as negative
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param accountAmount Normal account amount
     * @param peoplAmount People account amount          
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    public long addBorrowFX(long accId, long peopId, long catId, Date date, String comment,
            long accountAmount, long peoplAmount)
                                throws AccountException, PeopleAccountException
    {        
        cm.open();
        normalExists(accId);
        normalNotClosed(accId);
        peopleExists(peopId);
        catExists(catId);
        long result = addTransferFX(peopId, accId, catId, date, comment, peoplAmount,
                        accountAmount, TransactionType.BORROW, false, 0, 0, 0);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit transaction as Borrow. If transaction is not borrow transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param accountAmount Normal account amount
     * @param peoplAmount People account amount     
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public void editBorrowFX(long trnId, long accId, long peopId, Date date, String comment,
            long accountAmount, long peoplAmount,  long rate, long pos, boolean Reciprocal)
                                throws AccountException, PeopleAccountException
    {
        
    }
    
    /**
     * Edit transaction as Borrow. If transaction is not borrow transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param peopId People Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Normal account amount
     * @param accountAmount if true, the amount is from normal account, else the amount
     * is from people account. The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param Reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     */
    public void editBorrowFX(long trnId, long accId, long peopId, Date date, String comment,
            long amount, boolean accountAmount,  long rate, long pos, boolean Reciprocal)
                                throws AccountException, PeopleAccountException
    {
        
    }
    
    // </editor-fold>    
    
    // TODO - Add edit method for Tranfer Deleted
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods"> 
    
    // <editor-fold defaultstate="collapsed" desc="Exists Methods"> 
    
    /**
     * Checks if normal account exists with specified id
     * @param id Account id
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} 
     * if account was not found with specified id
     */
    private void normalExists(long id) throws AccountException
    {
        if(!normal.exists(id))
            throw new AccountException(AccountException.NOT_EXISTS,
                    "No account exists with id: " + id);
    }
    
    /**
     * Checks if normal account is closed.
     * @param id Account id
     * @throws AccountException with state {@link AccountException#IS_CLOSED}
     * if account was closed
     */
    private void normalNotClosed(long id) throws AccountException
    {
        if(normal.isClosed(id))
            throw new AccountException(AccountException.IS_CLOSED, 
                    String.format("Account with id: %d is closed", id));
    }
    
    /**
     * Checks if people account exists with specified id
     * @param id Account id
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS} 
     * if account was not found with specified id
     */
    private void peopleExists(long id) throws PeopleAccountException
    {
        if(!people.exists(id))
            throw new AccountException(AccountException.NOT_EXISTS,
                    "No account exists with id: " + id);
    }
    
    /**
     * Checks if category exists with specified id.
     * @param id Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category was not found with specified id
     */
    private void catExists(long id) throws CategoryException
    {
        if(!cat.exists(id))
            throw new CategoryException(CategoryException.NOT_EXISTS,
                    "No category exists with id: " + id);
    }
    
    /**
     * Checks if category exists with specified id.
     * @param id Category id
     * @throws SubCategoryException with state {@link CategoryException#NOT_EXISTS}
     * if sub category was not found with specified id
     */
    private void subExists(long id) throws SubCategoryException
    {
        if(!sub.exists(id))
            throw new SubCategoryException(SubCategoryException.NOT_EXISTS,
                    "No sub category exists with id: " + id);
    }
    
    /**
     * Checks if payee exists with specified id.
     * @param id Category id
     * @throws PayeeException with state {@link PayeeException#NOT_EXISTS} 
     * if payee was not found with specified id
     */
    private void payeeExists(Long id) throws PayeeException
    {
        if(id != null && !payee.exists(id))
            throw new PayeeException(PayeeException.NOT_EXISTS,
                    "No payee exists with id: " + id);
    }
    
    /**
     * Checks if transaction exists with specified id.
     * @param id Category id
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     */
    private void transExists(Long id) throws TransactionException
    {
        if(id != null && !trans.exists(id))
            throw new PayeeException(PayeeException.NOT_EXISTS,
                    "No transaction exists with id: " + id);
    }
    
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Income / Expense Methods"> 
    
    /**
     * Add Income or Expense transaction to account, category and sub categories. 
     * The amount will be registered as positive if income, as negative if expense.
     * It can be used to update a transaction. Reversing and cleaning previous
     * transaction's effect.
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. Amount will be registered as positive if income, even
     * if provided in negative number. Or it will be registered as negative if expense, even
     * if provided in positive number
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @param income Whether to add an income transaction  or not. If not, it will
     * be considered as expense transaction     
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws SubCategoryException with state:
     * <p>
     * 1. {@link SubCategoryException#NOT_EXISTS} if one of the sub categories 
     * provided does not exists
     * </p>
     * <p>
     * 2. {@link SubCategoryException#NOT_LINKED} if one of the sub categories
     * is not linked with provided category
     * </p>
     * <p>
     * 3. {@link SubCategoryException#NOT_EQUAL} if sum of array of sub category
     * amount is not equal to transaction amount
     * </p>
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     * @throws PayeeException with state {@link PayeeException#NOT_EXISTS} if
     * payee does not exists with specified id
     */
    private long addIncomeExpense(long accId, long catId, Long payId, Date date, String comment,
            long amount, long subId[], long subAmount[], boolean income) throws AccountException, CategoryException,
                    PayeeException, SubCategoryException, IllegalArgumentException
    {
        return addIncomeExpense(accId, catId, payId, date, comment, amount, subId, subAmount,
                income, false, 0, 0, 0);
    }
    
    /**
     * Add Income or Expense transaction to account, category and sub categories. 
     * The amount will be registered as positive if income, as negative if expense.
     * It can be used to update a transaction. Reversing and cleaning previous
     * transaction's effect.
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. Amount will be registered as positive if income, even
     * if provided in negative number. Or it will be registered as negative if expense, even
     * if provided in positive number
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @param income Whether to add an income transaction  or not. If not, it will
     * be considered as expense transaction
     * @param update Indicates to use the function to update a previous transaction.
     * if true, transaction id must be provided
     * @param trnId Transaction id. Must be provided if this function is used for
     * means of updating a previous transaction.
     * @param entAcc Entry id for Normal Account entry
     * @param entCat Entry id for Category entry
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws SubCategoryException with state:
     * <p>
     * 1. {@link SubCategoryException#NOT_EXISTS} if one of the sub categories 
     * provided does not exists
     * </p>
     * <p>
     * 2. {@link SubCategoryException#NOT_LINKED} if one of the sub categories
     * is not linked with provided category
     * </p>
     * <p>
     * 3. {@link SubCategoryException#NOT_EQUAL} if sum of array of sub category
     * amount is not equal to transaction amount
     * </p>
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     * @throws PayeeException with state {@link PayeeException#NOT_EXISTS} if
     * payee does not exists with specified id
     */
    private long addIncomeExpense(long accId, long catId, Long payId, Date date, String comment,
            long amount, long subId[], long subAmount[], boolean income, 
            boolean update, long trnId, long entAcc, long entCat) throws AccountException, CategoryException,
                    PayeeException, SubCategoryException, IllegalArgumentException
    {        
        normalExists(accId);
        normalNotClosed(accId);
        catExists(catId);        
        payeeExists(payId);
        if(income)
        {
            if(amount < 0)            
                amount = Math.abs(amount);            
        }
        else
        {
            if(amount > 0)
                amount *= -1;
        }
                
        boolean hasSubs = false;
        boolean fxNeeded = false;
        Currency currency = null;
        if(subId != null && subAmount != null)
        {
            if(subId.length != subAmount.length)
                throw new IllegalArgumentException("Sub Category id array does not"
                        + " match in size with sub category amount array)");            
            
            for(long s : subId)
                subExists(s);
            
            long sum = 0;
            if(income)
            {
                for(int i = 0; i < subAmount.length; i++)
                {
                    if(subAmount[i] < 0)
                        subAmount[i] = Math.abs(subAmount[i]);
                    sum += subAmount[i];            
                }                
            }
            else
            {
                for(int i = 0; i < subAmount.length; i++)
                {
                    if(subAmount[i] > 0)
                        subAmount[i] *= -1;
                    sum += subAmount[i];            
                }                
            }
            if(sum != amount)
                throw new SubCategoryException(SubCategoryException.NOT_EQUAL, 
                        "Sum of array of Sub-category amount does not equal to transaction amount");
            
            List<Long> subs = sub.getAllSubs(catId);
            for(long s : subId)
                if(!subs.contains(s))
                    throw new SubCategoryException(SubCategoryException.NOT_LINKED, 
                            String.format("Sub-category of id: %d is not linked"
                                    + " with category with id: %d", s, catId));
            
            hasSubs = true;
        }
        
        // TODO - Check if account currency matches primary currency
        // Else do some fx operations
        // Don't forget to use some kind of caching system                
        long id;
        if(update)
            id = trnId;
        else
            id = trans.insert(date, comment, payId, TransactionType.INCOME);
        // Account Entry
        long curId = normal.get(accId).getCurrency().getId();
        if(curId != config.getPrimaryCurrency())
        {
            fxNeeded = true;                        
            currency = getCurrency(curId);
        }        
        
        if(update)
            entry.update(entAcc, id, accId, amount);
        else
            entry.insert(id, accId, amount);
        updateBalance(accId, date, amount, BalanceType.NORMAL, true);        
        // Category Entry
        if(fxNeeded)
        {
            if(currency.isReciprocal())
                amount = FXUtil.multiply(amount, currency.getDecimal(), currency.getRate(), currency.getPos());
            else
                amount = FXUtil.divide(amount, currency.getDecimal(), currency.getRate(), currency.getPos());                
            
            // If fx amount is so low its 0, at least record it as one cent
            // TODO - Why add one cent? Explain more here. Check if needed
            if(amount == 0)
                amount = 1;
        }
        
        if(update)
            entry.update(entCat, id, catId, amount);
        else
            entry.insert(id, catId, amount);        
        updateBalance(catId, date, amount, BalanceType.OTHER, true);                        
                
        // Sub-category Entry
        if(hasSubs)
        {
            if(fxNeeded)
            {
                long sum = 0;
                if(currency.isReciprocal())
                {
                    for(int i =0; i < subAmount.length; i++)
                    {   
                        subAmount[i] = FXUtil.multiply(subAmount[i], currency.getDecimal(),
                                currency.getRate(), currency.getPos());                        
                        sum += subAmount[i];
                    }
                }
                else
                {
                    for(int i =0; i < subAmount.length; i++)
                    {   
                        subAmount[i] = FXUtil.divide(subAmount[i], currency.getDecimal(),
                                currency.getRate(), currency.getPos());                        
                        sum += subAmount[i];
                    }
                }
                long diff = amount - sum;
                
                if(diff != 0)
                    subAmount[0] += diff;
            }
            
            for(int i =0; i < subId.length; i++)
            {   
                entry.insert(id, subId[i], subAmount[i]);                
                updateBalance(subId[i], date, subAmount[i], BalanceType.OTHER, true);                
            }
        }        
        
        return id;
    }    
    
    /**
     * Edit transaction as Income or expense. If transaction is not an income/expense
     * transaction , it will change accordingly. 
     * @param trnId Transaction id
     * @param accId Normal Account id
     * @param catId Category id
     * @param payId Payee id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @param subId Array of sub category's id. all provided IDs must be related
     * and sub of given category (which was specified in catId parameter)
     * @param subAmount Array of sub Category amounts. Sum of array amounts must
     * be equal to transaction amount
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if account does not exists with 
     * specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if account is closed
     * </p>
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     * @throws SubCategoryException with state:
     * <p>
     * 1. {@link SubCategoryException#NOT_EXISTS} if one of the sub categories 
     * provided does not exists
     * </p>
     * <p>
     * 2. {@link SubCategoryException#NOT_LINKED} if one of the sub categories
     * is not linked with provided category
     * </p>
     * @throws IllegalArgumentException if subId array and subAmount array are not
     * equal in size
     */
    private void editIncomeExpense(long trnId, long accId, long catId, Long payId, Date date,
            String comment, long amount, long subId[], long subAmount[], boolean income) 
            throws TransactionException, AccountException, CategoryException,
                        SubCategoryException, IllegalArgumentException
    {
        amount = Math.abs(amount);
        cm.open();        
        transExists(trnId);              
        normalExists(accId);
        normalNotClosed(accId);
        catExists(catId);
        
        boolean approved = true;
        
        Transaction trn = getTransaction(trnId);
        if(trn.getType() == TransactionType.INCOME ||
                trn.getType() == TransactionType.EXPENSE)
        {     
            parseIncomeExpense(trn);
            
            // Checking for minor changes
            if(oldTrn.getDate() != date || 
                    oldAcc.getAccount().getId() != accId ||
                    oldCat.getAccount().getId() != catId || 
                    Math.abs(oldAcc.getAmount()) != amount )             
            {
                approved = false;                
            }
            
            // Checking if changes occured in sub categories
            if(approved && subId != null)
            {
                if(subId.length != subAmount.length)
                    throw new IllegalArgumentException("Sub Category id array does not"
                        + " match in size with sub category amount array)");            
                
                if(subId.length != oldSubs.size())
                    approved = false;
                else
                {
                    for(int i =0; i < subId.length; i++)
                    {                    
                        boolean found = false;
                        for(Entry sub: oldSubs)
                            if(sub.getAccount().getId() == subId[i])
                            {
                                found = true;
                                if(sub.getAmount() != subAmount[i])
                                {
                                    approved = false;
                                    break;
                                }
                            }

                        if(!found)
                            approved = false;

                        if(!approved)
                            break;
                    }
                }
            }
            
            // If approved for minor change, no need for reversal
            if(approved)                
                trans.update(trnId, date, comment, payId, 
                        income? TransactionType.INCOME : TransactionType.EXPENSE);                
            
            // Else reverse effect
            else
            {
                reverseIncomeExpense();
                
                addIncomeExpense(accId, catId, payId, date, comment, amount, subId,
                        subAmount, income, true, trnId, oldAcc.getId(), oldCat.getId());
                
                emptyReversal();
            }            
        }        
                
        else
        {
            if (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW )
            {
                if (trn.isFx())
                {
                    parseTransferFX(trn);
                    reverseTransferFX();
                }
                else
                {
                    parseTransfer(trn);
                    reverseTransfer();
                }
            }                        
            
            else if (trn.getType() == TransactionType.TRANFER_DELETED)
            {
                throw new UnsupportedOperationException(
                        "Editing from a deleted transaction is not allowed");
            }
            
            addIncomeExpense(accId, catId, payId, date, comment, amount, subId,
                subAmount, income, true, trnId, oldFrom.getId(), oldTo.getId());                
            emptyReversal();
        }
        
        cm.close();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Parse Methods"> 
    
    /**
     * Analyze Income or Expense transaction. Transaction and associated entries are retrieved.
     * @param transaction Transaction to parse
     */
    private void parseIncomeExpense(Transaction transaction)
    {
        oldTrn = transaction;
        List<Entry> entries = oldTrn.getEntries();

        // if it has no sub categories
        if(entries.size() == 2)
        {
            if(entries.get(0).getAccount().getType() == AccountType.NORMAL)
            {
                oldAcc = entries.get(0);
                oldCat = entries.get(1);
            }
            else
            {
                oldAcc = entries.get(1);
                oldCat = entries.get(0);
            }                    
        }
        // else it has sub categories
        else
        {
            oldSubs = new ArrayList<>(entries.size() - 2);
            for(int i = 0; i < entries.size(); i++)
            {
                AccountType type = entries.get(i).getAccount().getType();
                switch(type)
                {
                    case NORMAL:
                        oldAcc = entries.get(i);
                        break;
                    case CATEGORY:
                        oldCat = entries.get(i);
                        break;
                    case SUB_CATEGORY:
                        oldSubs.add(entries.get(i));
                        break;
                }                        
            }
        }        
    }
    
    /**
     * Analyze Transfer, Lend or Borrow transaction. Transaction and associated entries are retrieved.
     * @param transaction Transaction to parse
     */
    private void parseTransfer(Transaction transaction)
    {
        oldTrn = transaction;
        List<Entry> entries = oldTrn.getEntries();

        
        if(entries.get(0).getAmount() <= 0)
        {
            oldFrom = entries.get(0);
            oldTo = entries.get(1);
        }
        else
        {
            oldFrom = entries.get(1);
            oldTo = entries.get(0);
        }                                   
    }
    
    /**
     * Analyze TransferFX, LendFX or BorrowFX transaction. 
     * Transaction and associated entries are retrieved.
     * @param transaction Transaction to parse
     */
    private void parseTransferFX(Transaction transaction)
    {
        oldTrn = transaction;
        List<Entry> entries = oldTrn.getEntries();

        // If there is registered FX difference
        if(entries.size() > 2)
        {        
            // Choose who is From A/C, To A/C & FX Difference Category
            if(entries.get(2).getAccount().getType() == AccountType.CATEGORY)
            {
                if(entries.get(0).getAmount() <= 0)
                {
                    oldFrom = entries.get(0);
                    oldTo = entries.get(1);
                }
                else
                {
                    oldFrom = entries.get(1);
                    oldTo = entries.get(0);
                }
                oldCat = entries.get(2);
            }
            else if(entries.get(1).getAccount().getType() == AccountType.CATEGORY)
            {
                if(entries.get(0).getAmount() <= 0)
                {
                    oldFrom = entries.get(0);
                    oldTo = entries.get(2);
                }
                else
                {
                    oldFrom = entries.get(2);
                    oldTo = entries.get(0);
                }
                oldCat = entries.get(1);
            }
            else if(entries.get(0).getAccount().getType() == AccountType.CATEGORY)
            {
                if(entries.get(1).getAmount() <= 0)
                {
                    oldFrom = entries.get(1);
                    oldTo = entries.get(2);
                }
                else
                {
                    oldFrom = entries.get(2);
                    oldTo = entries.get(1);
                }
                oldCat = entries.get(0);
            }
        }
        else
            parseTransfer(transaction);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Reverse Methods"> 
    
    /**
     * Reverse Income or Expense Transaction. Account and Category
     * entry are not deleted, but affect balances and dated balances are reversed. 
     * All sub category's entries will be deleted with their affected balances and
     * dated balances reversed.     
     * @throws IllegalStateException if no transaction was parsed before reversal
     */
    private void reverseIncomeExpense()
    {
        if(oldTrn == null)
            throw new IllegalStateException("No transaction parsed for reversal");
        
        // Reverse sub categories
        if(oldSubs != null)
        {
            for(Entry oldSub: oldSubs)
            {
                entry.delete(oldSub.getId());
                updateBalance(oldSub.getAccount().getId(), oldTrn.getDate(), oldSub.getAmount() * -1,
                    BalanceType.OTHER, true);
            }
        }
        
        // Reverse Normal Account
        updateBalance(oldAcc.getAccount().getId(), oldTrn.getDate(), 
                oldAcc.getAmount() * -1, BalanceType.NORMAL, true);
        // Reverse Category
        updateBalance(oldCat.getAccount().getId(), oldTrn.getDate(),
                oldCat.getAmount() * -1, BalanceType.OTHER, true);        
    }
    
    /**
     * Reverse Transfer, Lend or Borrow Transaction. From A/C and To A/C
     * entry are not deleted, but affect balances and dated balances are reversed.      
     * @throws IllegalStateException if no transaction was parsed before reversal
     */
    private void reverseTransfer()
    {
        if(oldTrn == null)
            throw new IllegalStateException("No transaction parsed for reversal");
                
        // Reverse From A/C
        updateBalance(oldFrom.getAccount().getId(), oldTrn.getDate(), oldFrom.getAmount() * -1, 
            oldTrn.getType() == TransactionType.BORROW ? BalanceType.PEOPLE : BalanceType.NORMAL, true);
        // Reverse To A/C
        updateBalance(oldTo.getAccount().getId(), oldTrn.getDate(), oldTo.getAmount() * -1, 
            oldTrn.getType() == TransactionType.LEND ? BalanceType.PEOPLE : BalanceType.NORMAL, true);        
    }
    
    /**
     * Reverse TransferFX, LendFX or BorrowFX Transaction. From A/C and To A/C
     * entry are not deleted, but affect balances and dated balances are reversed.      
     * @throws IllegalStateException if no transaction was parsed before reversal
     */
    private void reverseTransferFX()
    {
        reverseTransfer();
        
        // Reverse FX difference if exists
        if(oldCat != null)
        {
            updateBalance(oldCat.getAccount().getId(), oldTrn.getDate(),
                oldCat.getAmount() * -1, BalanceType.OTHER, true);        
        }
        if(oldTrn.isFx())
            trans.deleteFX(oldTrn.getId());
    }
    
    /**
     * Nullifies variables used in reversal process. Used to guarantee that a
     * parse process has been called before every reversal, and that no double
     * reversal has been performed
     */
    private void emptyReversal()
    {
        oldTrn = null;
        oldAcc = null;
        oldCat = null;
        oldSubs = null;
        oldFrom = null;
        oldTo = null;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Transfer Methods"> 
    
    /**
     * Add Transfer transaction. From account will be registered as negative, while
     * to account will be registered as positive
     * @param fromId From Account id
     * @param toId To Account id     
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount. From account will be registered as negative, while
     * to account will be registered as positive
     * @param type Transaction type. See {@link TransactionType}
     * @param update Whether it is an update or not. If it is, transaction id,
     * from entry id and to entry id must be provided.
     * @param trnId Transaction id. Provide if its an update
     * @param fromEnt Entry id of From A/C. Provide if its an update
     * @param toEnt  Entry id of To A/C. Provide if its an update
     * @return Transaction id generated
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws CurrencyException with state {@link CurrencyException#NOT_SAME} if
     * from and to accounts' currency do not match
     */
    private long addTransfer(long fromId, long toId, Date date, String comment,
            long amount, TransactionType type, boolean update, long trnId,
            long fromEnt, long toEnt) throws AccountException, CurrencyException
    {
        // TODO - Use caching techniques
        amount = Math.abs(amount);                
        long id;
        
        if(update)
        {
            id = trnId;
            trans.update(id, date, comment, null, type);
        }
        else
            id = trans.insert(date, comment, null, type);
        
        long fromCurId = 0; 
        long toCurId = 0; 
        
        switch (type)
        {
            case TRANSFER:
                fromCurId = normal.get(fromId).getCurrency().getId();
                toCurId = normal.get(toId).getCurrency().getId();
                break;
            case LEND:
                fromCurId = normal.get(fromId).getCurrency().getId();
                toCurId = people.get(toId).getCurrency().getId();
                break;
            case BORROW:
                fromCurId = people.get(fromId).getCurrency().getId();
                toCurId = normal.get(toId).getCurrency().getId();
                break;            
        }
        
        if(fromCurId != toCurId)
            throw new CurrencyException(CurrencyException.NOT_SAME,
                    "Tranfer accounts' currency do not match");
        
        // From Account Entry         
        if(update)
            entry.update(fromEnt, id, fromId, amount * -1);
        else    
            entry.insert(id, fromId, amount * -1);
        updateBalance(fromId, date, amount * -1, BalanceType.NORMAL, true);        
        
        // To Account Entry     
        if(update)
            entry.update(toEnt, id, toId, amount);
        else
            entry.insert(id, toId, amount);
        updateBalance(toId, date, amount, BalanceType.NORMAL, true);        
        
        return id;
    }
    
    
    /**
     * Add Transfer transaction between accounts of different currencies. 
     * From account will be registered as negative, while to account will
     * be registered as positive. If there
     * is any difference, it will recorded in provided category
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction Date
     * @param comment Transaction comment
     * @param fromAmount From Amount
     * @param toAmount To Amount     
     * @param type Transaction type. See {@link TransactionType}
     * @param update Determines this function used as for adding or updating a 
     * transaction. In case of update, Transaction id, From Entry, To Entry and
     * Category Entry must be provided
     * @param trnId Transaction id. Must be provided if its an update
     * @param entFrom From Entity. Must be provided if its an update
     * @param entTo To Entity. Must be provided if its an update     
     * @return Transaction id generated
     */
    private long addTransferFX(long fromId, long toId, long catId, Date date, String comment,
            long fromAmount, long toAmount, TransactionType type, boolean update,
            long trnId, long entFrom, long entTo)
    {
        boolean reciprocal; // True means rate = From Amount / To Amount
        long fromCurId = 0; 
        long toCurId = 0; 
        
        switch (type)
        {
            case TRANSFER:
                fromCurId = normal.get(fromId).getCurrency().getId();
                toCurId = normal.get(toId).getCurrency().getId();
                break;
            case LEND:
                fromCurId = normal.get(fromId).getCurrency().getId();
                toCurId = people.get(toId).getCurrency().getId();
                break;
            case BORROW:
                fromCurId = people.get(fromId).getCurrency().getId();
                toCurId = normal.get(toId).getCurrency().getId();
                break;            
        }
        Currency fromCur = getCurrency(fromCurId);
        Currency toCur = getCurrency(toCurId);
                
        // The following determines whether rate will be reciprocal or not
        // Reciprocal indacates that rate = From Amount / To Amount
        if(fromCur.isReciprocal() && !toCur.isReciprocal())        
            reciprocal = false;        
        else if(toCur.isReciprocal() && !fromCur.isReciprocal())                    
            reciprocal = true;        
        else
        {
            BigDecimal fromRate = FXUtil.makeAmount(fromCur.getRate(), fromCur.getPos());
            BigDecimal toRate = FXUtil.makeAmount(toCur.getRate(), toCur.getPos());
            
            reciprocal = fromRate.compareTo(toRate) < 0;
        }
        
        Map<String, Long> fxRate;        
        
        if(reciprocal)
            fxRate = FXUtil.getRate(fromAmount, fromCur.getDecimal(), toAmount, toCur.getDecimal());            
        else
            fxRate = FXUtil.getRate(toAmount, toCur.getDecimal(), fromAmount, fromCur.getDecimal());
        
        return addTransferFX(fromId, toId, catId, date, comment, fromAmount,
                    toAmount, type, fxRate.get("rate"), fxRate.get("pos").intValue(), reciprocal,
                    update, trnId, entFrom, entTo);
    }
    
    /**
     * Add Transfer transaction between accounts of different currencies. 
     * From account will be registered as negative, while to account will
     * be registered as positive. This method takes only one amount for (from 
     * account) or (to account). The other will be calculated by fx rate. If there
     * is any difference, it will recorded in default category
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount From or to Amount
     * @param fromAmount if true, the amount is for (From Account), else the amount
     * is for (to Account). The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @param type Transaction type
     * @param update Determines this function used as for adding or updating a 
     * transaction. In case of update, Transaction id, From Entry, To Entry and
     * Category Entry must be provided
     * @param trnId Transaction id. Must be provided if its an update
     * @param entFrom From Entity. Must be provided if its an update
     * @param entTo To Entity. Must be provided if its an update     
     * @return Transaction id generated     
     */
    private long addTransferFX(long fromId, long toId, long catId, Date date, String comment,
            long amount, boolean fromAmount,  long rate, int pos,
            boolean reciprocal, TransactionType type, boolean update, long trnId, long entFrom,
            long entTo)
    {
        long otherId;
        if(fromAmount)        
            otherId = toId;
        else
            otherId = fromId;
        
        long curId;        
        if(type == TransactionType.LEND && fromAmount)
            curId= people.get(otherId).getCurrency().getId();
        else if(type == TransactionType.BORROW && !fromAmount)
            curId= people.get(otherId).getCurrency().getId();
        else
            curId= normal.get(otherId).getCurrency().getId();
            
        
        Currency currency = getCurrency(curId);        

        long otherAmount = FXUtil.makeFX(amount, currency.getDecimal(), rate, pos, reciprocal);        
        
        
        if(fromAmount)        
            return addTransferFX(fromId, toId, catId, date, comment, amount,
                otherAmount, TransactionType.TRANSFER, rate, pos, reciprocal, update,
                trnId, entFrom, entTo);
        
        else
            return addTransferFX(fromId, toId, catId, date, comment, otherAmount, amount, 
                TransactionType.TRANSFER, rate, pos, reciprocal, update, trnId, entFrom, entTo);            
    }
    
    /**
     * Add Transfer transaction between accounts of different currencies. 
     * From account will be registered as negative, while to account will
     * be registered as positive. If there
     * is any difference, it will recorded in provided category
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction Date
     * @param comment Transaction comment
     * @param fromAmount From Amount
     * @param toAmount To Amount     
     * @param type Transaction type. See {@link TransactionType}
     * @param useFXRate Whether to use FX rate for approving FX rather than difference
     * between from account and to Account. If true, from or to amount might be
     * adjusted 
     * @param rate FX rate
     * @param pos FX rate decimal position
     * @param reciprocal Whether fx rate is reciprocal or not. Pair is based as
     * (From Account / To Account)
     * @param update Determines this function used as for adding or updating a 
     * transaction. In case of update, Transaction id, From Entry, To Entry and
     * Category Entry must be provided
     * @param trnId Transaction id. Must be provided if its an update
     * @param entFrom From Entity. Must be provided if its an update
     * @param entTo To Entity. Must be provided if its an update     
     * @return Transaction id generated
     */
    private long addTransferFX(long fromId, long toId, long catId, Date date, String comment,
            long fromAmount, long toAmount, TransactionType type, long rate, 
            int pos, boolean reciprocal, boolean update, long trnId, long entFrom,
            long entTo) throws AccountException
    {
        boolean needFX = false;
        long fxDiff = 0;
        
        // TODO - Use caching techniques
        if(fromAmount > 0)
            fromAmount *= -1;
        if(toAmount < 0)
            toAmount = Math.abs(toAmount);                
        
        long id;
        if(update)
        {
            id = trnId;
            trans.update(id, date, comment, null, type);
        }
        else
            id = trans.insert(date, comment, null, TransactionType.TRANSFER);        
        
        long fromCurId = 0; 
        long toCurId = 0; 
        
        switch (type)
        {
            case TRANSFER:
                fromCurId = normal.get(fromId).getCurrency().getId();
                toCurId = normal.get(toId).getCurrency().getId();
                break;
            case LEND:
                fromCurId = normal.get(fromId).getCurrency().getId();
                toCurId = people.get(toId).getCurrency().getId();
                break;
            case BORROW:
                fromCurId = people.get(fromId).getCurrency().getId();
                toCurId = normal.get(toId).getCurrency().getId();
                break;            
        }
        
        // From Account Entry                
        if(update)
            entry.update(entFrom, id, fromId, fromAmount);
        else
            entry.insert(id, fromId, fromAmount);
        if(type == TransactionType.TRANSFER || type == TransactionType.LEND)
            updateBalance(fromId, date, fromAmount, BalanceType.NORMAL, true);
        else
            updateBalance(fromId, date, fromAmount, BalanceType.PEOPLE, true);
                
        // To Account Entry                
        if(update)
            entry.update(entTo, id, toId, toAmount);
        else
            entry.insert(id, toId, toAmount);
        if(type == TransactionType.TRANSFER || type == TransactionType.BORROW)
            updateBalance(toId, date, toAmount, BalanceType.NORMAL, true);
        else
            updateBalance(toId, date, toAmount, BalanceType.PEOPLE, true);        
        
        // FX procedure
        // TODO - check matching of from and to amounts against fx rate
        if(fromCurId != toCurId)
        {
            needFX = true;
            Currency primaryCur = config.Currency().get(config.getPrimaryCurrency());            
            if(fromCurId != config.getPrimaryCurrency())
            {                
                Currency currency = getCurrency(fromCurId);
                
                fromAmount = FXUtil.makeFX(fromAmount, currency.getDecimal(), 
                        currency.getRate(), currency.getPos(), currency.isReciprocal());                
            }
            if(toCurId != config.getPrimaryCurrency())
            {
                Currency currency = getCurrency(toCurId);                
                
                toAmount = FXUtil.makeFX(toAmount, currency.getDecimal(), 
                        currency.getRate(), currency.getPos(), currency.isReciprocal());
                
            }
            fromAmount = Math.abs(fromAmount);
            fxDiff = toAmount - fromAmount;
        }
        
        // Category Entry                
        // TODO - Add record to TransactionFX table too
        if(fxDiff != 0)
        {
            entry.insert(id, catId, fxDiff);
            updateBalance(catId, date, fxDiff, BalanceType.OTHER, true);            
        }
        
        // Transaction FX link
        if(needFX)
            trans.insertFX(id, rate, pos, reciprocal);        
        
        return id;
    }        
    
    /**     
     * Edit transaction as Transfer. If transaction is not a transfer transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount Transaction amount
     * @param type Transaction Type
     * @throws IllegalArgumentException if Transaction type is not Transfer,
     * Lend or Borrow
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    private void editTransfer(long trnId, long fromId, long toId, Date date, 
            String comment, long amount, TransactionType type) throws IllegalArgumentException,
            TransactionException, AccountException, PeopleAccountException, CategoryException
    {        
        cm.open();        
        transExists(trnId);      
        amount = Math.abs(amount);
        // TODO - Check that checking is only performed here and no place else                
        
        boolean approved = true;
        
        Transaction trn = getTransaction(trnId);
        
        // Transfer with no fx
        if( (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW) && 
                !trn.isFx() )
        {     
            if(type == TransactionType.TRANSFER)
            {
                normalExists(fromId);
                normalNotClosed(fromId);
                normalExists(toId);
                normalNotClosed(toId);
            }
            else if (type == TransactionType.LEND)
            {
                normalExists(fromId);
                normalNotClosed(fromId);
                peopleExists(toId);
            }
            else if (type == TransactionType.BORROW)
            {
                peopleExists(fromId);
                normalExists(toId);
                normalNotClosed(toId);
            }
            else
                throw new IllegalArgumentException(
                        "Transaction type expected to be either Tranfer, Lend or Borrow");
            
            parseTransfer(trn);
            
            // Checking for minor changes
            if(oldTrn.getDate() != date || 
                    oldFrom.getAccount().getId() != fromId ||
                    oldTo.getAccount().getId() != fromId)             
            {
                approved = false;                
            }
            
            // If approved for minor change, no need for reversal
            if(approved)                
            {
                trans.update(trnId, date, comment, null, type);                
                
                if( Math.abs(oldFrom.getAmount()) != Math.abs(amount))
                {
                    long amountDiff = oldFrom.getAmount() + amount;
                    // Update From A/C
                    updateBalance(oldFrom.getId(), date, amountDiff, 
                        oldTrn.getType() == TransactionType.BORROW ? 
                            BalanceType.PEOPLE : BalanceType.NORMAL, true);
                    // Update To A/C
                    updateBalance(oldTo.getId(), date, amountDiff * -1, 
                        oldTrn.getType() == TransactionType.LEND ? 
                            BalanceType.PEOPLE : BalanceType.NORMAL, true);
                }
            }
            
            // Else reverse effect
            else
            {
                reverseTransfer();
                addTransfer(fromId, toId, date, comment, amount, type, true, trnId,
                        oldFrom.getId(), oldTo.getId());
                emptyReversal();
            }            
        }        
        
        else
        {
            if (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW )
            {
                parseTransferFX(trn);
                reverseTransferFX();    
                addTransfer(fromId, toId, date, comment, amount, type, true, trnId,
                        oldFrom.getId(), oldTo.getId());
            }          
            else if (trn.getType() == TransactionType.INCOME ||
                     trn.getType() == TransactionType.EXPENSE)
            {
                parseIncomeExpense(trn);
                reverseIncomeExpense();
                addTransfer(fromId, toId, date, comment, amount, type, true, trnId,
                        oldAcc.getId(), oldCat.getId());
            }
            
            else if (trn.getType() == TransactionType.TRANFER_DELETED)
            {
                throw new UnsupportedOperationException(
                        "Editing from a deleted transaction is not allowed");
            }
            
            emptyReversal();
        }
        
        cm.close();
    }
    
    /**
     * Edit transaction as Transfer. If transaction is not a transfer transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction Date
     * @param comment Transaction comment
     * @param fromAmount From Amount
     * @param toAmount To Amount          
     * @param type Transaction Type
     * @throws IllegalArgumentException if Transaction type is not Transfer,
     * Lend or Borrow
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    private void editTransferFX(long trnId, long fromId, long toId, long catId, Date date,
            String comment, long fromAmount, long toAmount, TransactionType type)   
            throws IllegalArgumentException, TransactionException, AccountException,
                                            PeopleAccountException, CategoryException
    {        
        cm.open();
        transExists(trnId);        
        boolean approved = true;
        
        Transaction trn = getTransaction(trnId);
        
        // Transfer with FX
        if( (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW) && 
                trn.isFx() )
        {     
            if(type == TransactionType.TRANSFER)
            {
                normalExists(fromId);
                normalNotClosed(fromId);
                normalExists(toId);
                normalNotClosed(toId);
            }
            else if (type == TransactionType.LEND)
            {
                normalExists(fromId);
                normalNotClosed(fromId);
                peopleExists(toId);
            }
            else if (type == TransactionType.BORROW)
            {
                peopleExists(fromId);
                normalExists(toId);
                normalNotClosed(toId);
            }
            else
                throw new IllegalArgumentException(
                        "Transaction type expected to be either Tranfer, Lend or Borrow");
            
            catExists(catId);
            parseTransferFX(trn);
            
            // Checking for minor changes
            if(oldTrn.getDate() != date || 
                    oldFrom.getAccount().getId() != fromId ||
                    oldTo.getAccount().getId() != fromId || 
                    oldFrom.getAmount() != fromAmount ||
                    oldTo.getAmount() != toAmount)
                    
            {
                approved = false;                
            }
            
            
            // If approved for minor change, no need for reversal
            if(approved)                
            {
                trans.update(trnId, date, comment, null, type);                
            }
            
            // Else reverse effect
            else
            {
                reverseTransferFX();
                addTransferFX(fromId, toId, catId, date, comment, fromAmount, 
                        toAmount, type, true, trnId, oldFrom.getId(), oldTo.getId());
                emptyReversal();
            }            
        }        
        
        else
        {
            if (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW )
            {
                parseTransfer(trn);
                reverseTransfer();    
                addTransferFX(fromId, toId, catId, date, comment, fromAmount, 
                        toAmount, type, true, trnId, oldFrom.getId(), oldTo.getId());
            }          
            else if (trn.getType() == TransactionType.INCOME ||
                     trn.getType() == TransactionType.EXPENSE)
            {
                parseIncomeExpense(trn);
                reverseIncomeExpense();
                addTransferFX(fromId, toId, catId, date, comment, fromAmount, 
                        toAmount, type, true, trnId, oldAcc.getId(), oldCat.getId());
            }
            
            else if (trn.getType() == TransactionType.TRANFER_DELETED)
            {
                throw new UnsupportedOperationException(
                        "Editing from a deleted transaction is not allowed");
            }
            
            emptyReversal();
        }
        
        cm.close();
    }
    
    /**
     * Edit transaction as Transfer. If transaction is not a transfer transaction,
     * it will change accordingly. 
     * @param trnId Transaction id
     * @param fromId From Normal Account id
     * @param toId To Normal Account id
     * @param catId Category id. Will be used to record any differences due 
     * to foreign exchange
     * @param date Transaction date
     * @param comment Transaction comment
     * @param amount From or to Amount
     * @param fromAmount if true, the amount is for (From Account), else the amount
     * is for (to Account). The other unchosen account will be calculated
     * @param rate FX Rate
     * @param pos FX Rate decimal position
     * @param reciprocal Indicates that rate is reciprocal. False means the rate
     * pair is (from currency / to currency). If true, the rate will be 1/rate, as
     * if the rate indicates a pair of (to currency / from currency)
     * @param type Transaction type     
     * @throws IllegalArgumentException if Transaction type is not Transfer,
     * Lend or Borrow
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     * @throws AccountException with state:
     * <p>
     * 1. {@link AccountException#NOT_EXISTS}: if either account (from or to) 
     * does not exists with specified id
     * </p>
     * <p>
     * 2. {@link AccountException#IS_CLOSED}: if either account (from or to) is closed
     * </p>
     * @throws PeopleAccountException with state {@link PeopleAccountException#NOT_EXISTS}
     * if people account does not exists with specified id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS}
     * if category does not exists with specified id
     */
    private void editTransferFX(long trnId, long fromId, long toId, long catId, Date date,
            String comment, long amount, boolean fromAmount,  long rate, int pos,
            boolean reciprocal, TransactionType type) throws IllegalArgumentException,
            TransactionException, AccountException, PeopleAccountException, CategoryException
    {
        cm.open();
        transExists(trnId);
        amount = Math.abs(amount);        
        boolean approved = true;
        
        Transaction trn = getTransaction(trnId);
        
        // Transfer with FX
        if( (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW) && 
                trn.isFx() )
        {     
            if(type == TransactionType.TRANSFER)
            {
                normalExists(fromId);
                normalNotClosed(fromId);
                normalExists(toId);
                normalNotClosed(toId);
            }
            else if (type == TransactionType.LEND)
            {
                normalExists(fromId);
                normalNotClosed(fromId);
                peopleExists(toId);
            }
            else if (type == TransactionType.BORROW)
            {
                peopleExists(fromId);
                normalExists(toId);
                normalNotClosed(toId);
            }
            else
                throw new IllegalArgumentException(
                        "Transaction type expected to be either Tranfer, Lend or Borrow");
            
            catExists(catId);
            parseTransferFX(trn);
            
            // Checking for minor changes
            if(oldTrn.getDate() != date || 
                    oldFrom.getAccount().getId() != fromId ||
                    oldTo.getAccount().getId() != fromId)
                    
            {
                approved = false;                
            }
            // Check for change in amount
            if(approved)
            {
                if( (fromAmount && oldFrom.getAmount() != amount) ||
                    (!fromAmount && oldTo.getAmount() != amount)    )
                    approved = false;
            }
            
            
            // If approved for minor change, no need for reversal
            if(approved)                
            {
                trans.update(trnId, date, comment, null, type);                
            }
            
            // Else reverse effect
            else
            {
                reverseTransferFX();
                addTransferFX(fromId, toId, catId, date, comment, amount, fromAmount,
                        rate, pos, reciprocal, type, true, trnId, oldFrom.getId(), oldTo.getId());
                emptyReversal();
            }            
        }        
        
        else
        {
            if (trn.getType() == TransactionType.TRANSFER ||
                trn.getType() == TransactionType.LEND ||
                trn.getType() == TransactionType.BORROW )
            {
                parseTransfer(trn);
                reverseTransfer();    
                addTransferFX(fromId, toId, catId, date, comment, amount, fromAmount,
                        rate, pos, reciprocal, type, true, trnId, oldFrom.getId(), oldTo.getId());
            }          
            else if (trn.getType() == TransactionType.INCOME ||
                     trn.getType() == TransactionType.EXPENSE)
            {
                parseIncomeExpense(trn);
                reverseIncomeExpense();
                addTransferFX(fromId, toId, catId, date, comment, amount, fromAmount,
                        rate, pos, reciprocal, type, true, trnId, oldAcc.getId(), oldCat.getId());
            }
            
            else if (trn.getType() == TransactionType.TRANFER_DELETED)
            {
                throw new UnsupportedOperationException(
                        "Editing from a deleted transaction is not allowed");
            }
            
            emptyReversal();
        }
        
        cm.close();
    }
    
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Other"> 
    
    /**
     * Retrieve Transaction Entity
     * @param id Transaction id
     * @return Transaction Entity
     * @throws TransactionException with state {@link TransactionException#NOT_EXISTS}
     * if transaction does not exists with specified id
     */
    private Transaction getTransaction(long id)
    {
        transExists(id);
        Transaction result = trans.get(id);
        List<Entry> entries = entry.getForTransaction(id);
        result.setEntries(entries);
        
        for(Entry e: entries)
            e.setTransaction(result);
        
        return result;
    }
    
    /**
     * Update balance of an account by specified amount on specified date. Account
     * balance and dated balances are updated.
     * If account has balance on specified date, it will check for previous date.
     * If no previous balance exists, it will create a new balance of specified 
     * amount plus openingBalance of the account. All future balances will be updated
     * if exists. Balances are updated on the final day of a given month
     * @param id Account id
     * @param date Balance date
     * @param amount Balance amount
     * @param type Transaction Type.
     * @param monthly Whether given date must be seen as last day in the month
     */
    private void updateBalance(long id, Date date, long amount, BalanceType type, boolean monthly)
    {
        Date balDate = date;        
        if(monthly)
            balDate = DateTimeUtil.asLastDay(date);
        
        account.updateBalance(id, amount);
        
        if(bal.exists(id, balDate))
            bal.update(id, balDate, amount);
        else
        {
            Long prevBal = bal.getPreviousBalance(id, balDate);
            if(prevBal != null)
            {
                amount = prevBal + amount;
                bal.insert(id, balDate, amount);
            }
            else
            {
                switch(type)
                {
                    case NORMAL:
                        amount = normal.getOpeningBalance(id) + amount;                        
                        break;
                    case PEOPLE:
                        amount = people.getOpeningBalance(id) + amount;
                        break;
                }
            }
            bal.insert(id, balDate, amount);
        }        
        bal.updateFuture(id, balDate, amount);        
    }
    /**
     * Retrieve currency by checking Cache first. If not found, it will be 
     * retrieved from DB
     * @param curId Currency id
     * @return Currency entity
     */
    private Currency getCurrency(long curId)
    {
        Currency currency = config.Currency().get(curId);
        if(currency == null)
            currency = cur.get(curId);
        
        return currency;
    }
    
    /**
     * Balance Types. Used with updateBalance method
     */
    private enum BalanceType
    {
        NORMAL,
        PEOPLE,
        OTHER
    }
    
    // </editor-fold>
    
    // </editor-fold>
}
