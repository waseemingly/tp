package seedu.address.logic.commands.find;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
public class FindDeadlineCommandTest {
        private Model model;
        private Model expectedModel;

        @BeforeEach
        public void setUp() {
            model = new ModelManager();
            expectedModel = new ModelManager();
        }

        @Test
        public void execute_findDeadline_success() {
            assertCommandSuccess(new FindDeadlineCommand(), model, FindDeadlineCommand.MESSAGE_SUCCESS, model);
        }

}
