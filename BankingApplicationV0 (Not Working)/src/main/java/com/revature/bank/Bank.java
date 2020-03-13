package com.revature.bank;

import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.revature.bank.exceptions.IllegalUpdateException;
import com.revature.bank.exceptions.PopulationException;

public class Bank {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	public static final Locale DEF_LOCALE = Locale.US;
//	public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(DEF_LOCALE);

	private final Set<Person> clients;
	private final Map<TaxID, Person> taxIds;
	private final Map<Person, Set<BankAccount>> personToAccounts;
	private final Map<BankAccount, Set<Person>> accountToPersons;
	private final BankAccountGenerator accountGen;
	private final NumberFormat currencyFormat;

	public Bank(final BankAccountGenerator accountGen) {
		this(accountGen, DEF_LOCALE);
	}

	public Bank(final BankAccountGenerator accountGen, final Locale locale) {
		// create empty sets/maps
		this.clients = new HashSet<>();
		this.taxIds = new HashMap<>();
		this.personToAccounts = new HashMap<>();
		this.accountToPersons = new HashMap<>();
		this.accountGen = accountGen;
		this.currencyFormat = NumberFormat.getCurrencyInstance(locale);
	}

	/**
	 * Adds a new person to the bank's clients (this does not create an account).
	 * 
	 * @param person
	 * @throws PopulationException If the given person is already a client.
	 */
	public void addPerson(final Person person) throws PopulationException {
		if (clients.contains(person))
			throw new PopulationException(person + " already exists");

		// else add them to the population and update taxids map
		clients.add(person);
		taxIds.put(person.taxId, person);
	}

	public void addAccount(final Person person, final BankAccount account) {
		// TODO I suspect that this should not be public

		// this should really not happen under normal circumstances so this is an
		// unchecked (runtime) exception.
		if (personToAccounts.containsKey(person) && personToAccounts.get(person).contains(account))
			throw new IllegalUpdateException("Tried to re-add an account for " + person);

		addMappingSet(personToAccounts, person, account);
		addMappingSet(accountToPersons, account, person);
	}

	/**
	 * Opens an account with an initial amount of 0.
	 * 
	 * @param person
	 */
	public void openAccout(final Person person) {
		this.openAccount(person, 0);
	}

	/**
	 * Opens an account with the specified initial amount.
	 * 
	 * @param person
	 * @param initialAmount
	 */
	public void openAccount(final Person person, final double initialAmount) {
		// check to see if the bank has information for this person, if not, add them to
		// the bank
		if (!clients.contains(person)) {
			try {
				addPerson(person);
			} catch (PopulationException e) {
				// I'm wrapping in the try catch to avoid throwing the PopulationException. This
				// should never be entered because I'm checking whether or not the person is
				// already in the bank's list of clients.
				e.printStackTrace();
				throw new RuntimeException(
						"addPerson should not throw an exception from here since I am checking whether or not the person is already in the bank's clients");
			}
		}

		// create the new account
		final String accountNumber = accountGen.nextAccount();
		final BankAccount newAccount = new BankAccount(accountNumber, initialAmount, currencyFormat);

		this.addAccount(person, newAccount);
	}

	/**
	 * Helper method which adds a mapping from a key to a set.
	 * 
	 * @param <K>
	 * @param <E>
	 * @param map
	 * @param key
	 * @param element
	 */
	private static <K, E> void addMappingSet(final Map<K, Set<E>> map, final K key, final E element) {
		// get set
		Set<E> set = map.get(key);

		// see whether or not there is already a set being mapped to. If not (null)
		// create a new set and create the mapping.
		if (set == null) {
			set = new HashSet<>();
			map.put(key, set);
		}

		// either the set was already mapped or we just created it. Now add the element
		// to the mapped-to set.
		set.add(element);
	}
}
