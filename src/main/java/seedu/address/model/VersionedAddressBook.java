package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;


public class VersionedAddressBook extends AddressBook {
    private List<AddressBook> addressBookStateList;
    private ReadOnlyAddressBook initialAddressBook;
    private int currentStatePointer;
    public static final String INVALID_REDO_COMMAND = "You have reached the last step, unable to redo";
    public static final String INVALID_UNDO_COMMAND = "You have reached the first step, unable to undo";

    public VersionedAddressBook(ReadOnlyAddressBook initialAddressBook) {
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialAddressBook));
        currentStatePointer = 0;
    }

    public void commit(Model model) {
        if (currentStatePointer < addressBookStateList.size() - 1) {
            // If we're not at the latest state, remove the states after the current one
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }

        // Add the current state to the history
        AddressBook updatedAddressBook = new AddressBook(model.getAddressBook());
        addressBookStateList.add(updatedAddressBook);
        currentStatePointer++;
    }

    public void undo(Model model) throws CommandException {
        if (canUndo()) {
            currentStatePointer--;
            changeAddressBook(model);
        } else {
            throw new CommandException(INVALID_UNDO_COMMAND);
        }
    }

    public void redo(Model model) throws CommandException {
        if (canRedo()) {
            currentStatePointer++;
            changeAddressBook(model);
        } else {
            throw new CommandException(INVALID_REDO_COMMAND);
        }

    }

    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    public AddressBook getCurrentState() {
        return addressBookStateList.get(currentStatePointer);
    }
    public void changeAddressBook(Model model) {
        AddressBook newAddressBook = addressBookStateList.get(currentStatePointer);
        model.setAddressBook(newAddressBook);
    }
}

