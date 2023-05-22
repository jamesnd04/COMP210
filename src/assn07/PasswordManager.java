package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    private static int _size;

    public PasswordManager() {
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int hash = Math.abs(key.hashCode() % 50);
        Account fcheck = null;
        boolean added = false;
        if(key == null){
            return;
        }
        else{
            if (_passwords != null) {
                if (_passwords[hash] == null) {
                    Account temp = new Account(key, value);
                    _passwords[hash] = temp;
                    added = true;
                } else {
                    Account check = _passwords[hash];
                    if (check != null) {
                        while (check.getNext() != null) {
                            String checkS = (String) check.getWebsite();
                            String checkK = (String) key;
                            if (checkS != null && checkK != null && checkS.compareTo(checkK) == 0) {
                                _passwords[hash].setPassword(value);
                                return;
                            }
                            check = check.getNext();

                        }
                    }
                    if (check != null && check.getNext() == null) {
                        String checkS = (String) check.getWebsite();
                        String checkK = (String) key;
                        if (checkS != null && checkK != null && checkS.compareTo(checkK) == 0) {
                            check.setPassword(value);
                        } else {
                            Account temp = new Account(key, value);
                            check.setNext(temp);
                        }
                    }

                }
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int hash = Math.abs(key.hashCode() % 50);
        Account curr = _passwords[hash];
        while(curr != null){
            if(curr.getWebsite().equals(key)){
                return (V) curr.getPassword();
            }
            curr = curr.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < getPasswords().length - 1; i++){
            Account curr = _passwords[i];
            if (_passwords[i] != null){
                size++;
                while(curr.getNext() != null){
                    size++;
                    curr = curr.getNext();
                }
            }
        }
        return size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < getPasswords().length - 1; i++){
            Account curr = _passwords[i];
            if (_passwords[i] != null){
                while(curr.getNext() != null){
                    set.add((K)curr.getWebsite());
                    curr = curr.getNext();
                }
                set.add((K)curr.getWebsite());
            }
        }
        return set;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        V value = null;
        int hash = Math.abs(key.hashCode() % 50);
        Account curr = _passwords[hash];
        Account prev = null;

            while (curr != null) {
                    if (curr.getWebsite().equals(key)) {
                        value = (V) curr.getPassword();
                        if(prev != null){
                            prev.setNext(curr.getNext());
                        }
                        else{
                            _passwords[hash] = null;
                        }

                    }
                    prev = curr;
                    curr = curr.getNext();

            }


        return value;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < _passwords.length; i++){
            Account curr = _passwords[i];
            if (_passwords[i] != null) {
                while (curr != null) {
                    if (curr.getPassword().equals(value)) {
                        keyList.add((K) curr.getWebsite());
                    }
                    curr = curr.getNext();
                }
            }
        }
        return keyList;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        if (enteredPassword.equals(MASTER_PASSWORD)){return true;}
        else{
            return false;
        }
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}