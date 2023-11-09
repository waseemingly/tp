---
layout: page
title: Mingyu's Project Portfolio Page
---

### Project: CodeContact

CodeContact is a desktop address book solution for Software Engineering companies. The user interacts with it using a
CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: `Undo`
    * What it does: <br> Allows users to undo their previous command easily.
    * Justification: <br> Users can now effortlessly revert their previous actions, providing a more forgiving and 
      user-friendly experience. This feature adds a layer of convenience, especially in scenarios where users might 
      make unintended changes.
    * Highlights: <br> The undo feature has no limits to how many times you can undo and you can undo all the way to
      your first command.The command is as easy as `undo`, making this feature really easy to use.
    * Credits: <br> The general structure of how `undo` works is inspired from the AB-3 developer guide, which is 
      using a pointer that changes which addressbook to point to at each `undo` command. However, due to the nature and 
      large number of fields CodeContact have, alot more validation checks were added in. 


* **New Feature 2**: `Redo`
    * What it does: <br> Allows users to redo their previous undone command easily.
    * Justification: <br> By allowing users to easily redo their previous undone commands, we enhance the overall user 
      experience and streamline the workflow within the CLI and GUI interfaces. This feature provides a valuable 
      mechanism for users to correct mistakes, iterate through different states, and maintain a smooth interaction 
      with the application.
    * Highlights: <br> The undo feature has no limits to how many times you can redo, you can redo all the way to
      your first command. The system is also friendly to remind you when you have reached the latest stage and can no 
      longer redo. The command is as easy as `redo`, making this feature really easy to use. Coupled with the `undo`
      feature, it allows users to easily move front and back through their commands.
    * Credits: <br> The general structure of how `redo` works is inspired from the AB-3 developer guide, which is
      using a pointer that changes which addressbook to point to at each `redo` command. However, due to the nature and
      large number of fields CodeContact have, alot more validation checks were added in.


* **New Feature 3**: `Adding roles `
    * What it does: <br> Allows users to add different roles.
    * Justification: <br> This feature addresses the potential pitfalls associated with incorrect role assignments. 
      By allowing users to explicitly define and add developer or client roles, CodeContact ensures that the user base
      adheres to a standardized set of role names. This proactive approach minimizes the risk of mislabeling and 
      enhances the searchability of individuals within the application.
    * Highlights: <br> The feature caters to the unique needs of adding both developers and clients by providing 
      separate commands (`add-developer-role` and `add-client-role`). This level of specificity adds clarity to the 
      role assignment process. To facilitate users, there is also a pre-added list of roles for both clients and 
      developers so that users can seamlessly add in commands when they start using CodeContact and can slowly explore 
      other roles they wish to add thereafter. There are also validation checks done when adding roles to prevent you
      from adding repeated roles. 
    * Credits: N.A.


* **New Feature 4**: `Deleting roles `
    * What it does: <br> Allows users to delete different roles.
    * Justification: <br> This feature in CodeContact plays a pivotal role in user data management
      , allowing users to refine and update the list of available roles. Deleting roles is essential for maintaining 
      a clean and relevant role structure within the application. This feature empowers users to adapt the roles 
      available to their evolving needs, ensuring that the address book remains accurate.
    * Highlights: <br> The feature caters to the unique needs of deleting both developers and clients by providing
      separate commands (`delete-developer-role` and `delete-client-role`). To make sure that users don't accidentally 
      delete the roles when there are still developers and clients using that role, the feature searches through the
      developers and clients to make sure that no contact is using this role before deleting it. If there are still
      contacts assigned to these roles, deleting these roles will not be allowed. As CodeContact provides a 
      pre-added list of roles, users are also not allowed to delete these roles. 
    * Credits: N.A.


* **New Feature 5**: `list `
    * What it does: <br> Allows users to list project, developers or clients.
    * Justification: <br> The feature `list` the respective information when called and switches to the relevant tabs
      accordingly. This feature enhances user visibility and understanding of the available projects, developers, 
      and clients. By allowing users to list these entities, CodeContact ensures that users have quick access to 
      a comprehensive overview.
    * Highlights: <br> The "List" feature provides users with immediate access to critical information, allowing them 
      to view all existing projects, developers, or clients within the application. It plays an important part in 
      helping users identify the index of each contact so that they can easily execute other commands like `edit` or 
      `delete`.
    * Credits: <br> The general `list` implementation follows how it was implemented for AB3 but some modification have 
      been made such that it is customised to CodeContact.


* **Enhancements to existing features**:
    * Fixed the test cases to pass again (Pull requests [\#225](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/225))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=mingyu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=zoom&zA=mingyu-wan&zR=AY2324S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&zACS=241.3&zS=2023-09-22&zFS=mingyu&zU=2023-11-10&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)


* **Project management(team-based tasks contributions)**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub
    * Managed issues creating and some assigning throughout the milestones 
    * Managed the collaboration document and product demo submissions for tutorial


* **Documentation Contributions**:
    * User Guide Contributions:
        * Added documentation for the features `undo`,`redo`,`add-role` and `delete-role` [\#160](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/160)
        * Did cosmetic tweaks to existing documentation of features `list`: [\#160](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/160)
        * Wrote the content for `tutorial`: [\#162](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/162)
        * Modified and content and UI pictures for `Quick Start`: [\#162](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/162)
    * Developer Guide Contributions:
        * Added use case (abandoned due to a shift in project)
        * Added user stories 


* **Community (Review/mentoring contributions)**:
    * Reported bugs and suggestions for other teams in the cohort

