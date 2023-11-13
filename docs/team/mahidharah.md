---
layout: page
title: Mahidharah's Project Portfolio Page
---

### Project: CodeContact

CodeContact is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, CodeContact can get your contact management tasks done faster than traditional GUI apps.

Our product helps seamlessly integrate contact, client, and project management, simplifying access to coding-related contacts, facilitating collaboration, and offering command-line efficiency for project managers

Given below are my contributions to the project.


* **New Features**: `add-developer`, `add-client` & `add-project`
    * What it does: Adds a developer/client/project to the address book, making sure input parameters are valid and potential duplicates are found, highlighted to the user and rejected.
    * Justification: Decided to separate these as different commands instead of having a single `add` command to allow for more flexibility with parameters changes to these features in the future (from basic OOP principles). This also allows for more specific error messages to be displayed to users when they input invalid parameters, utilising polymorphism. 
    * Highlights: 
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in
      the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Features**: `delete-developer` & `delete-client`
    * What it does: Deletes a developer/client from the address book.
    * Justification: Decided to separate these as different commands instead of having a single `delete` command to allow for more flexibility with parameters changes to these features in the future (from basic OOP principles). This also allows for more specific error messages to be displayed to users when they input invalid parameters, utilising polymorphism.
    * Highlights:
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in
      the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*


* **New Feature**: `delete-project`
    * What it does: Deletes a project from the address book and accordingly removes it from the set of projects for all developers and clients.
    * Justification: I decided to implement the feature such that it triggers updates on developers and clients with hopes that this feature will be a major quality of life improvement for project managers, enabling them to remove projects easily without having to edit the information for relevant clients and developers to maintain integrity of the data.
    * Highlights: The feature uses the iterator java class and its for each remaining method effectively to simplify code and ensure efficiency. I also implemented such that the deadline field to be optional, and implemented the relevant logic.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in
      the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **Enhancements to existing features**:
    * Contributed to the GUI component of CodeContact by creating relevant classes and FXML files for developer and client cards (Pull request [\#79]())
    * Ensured functional improvements use developer and client classes instead of person class (Pull request [\#79](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/79))
    * Edited SampleData to include sample developers, clients and projects for easy testing for teammates (Pull request [\#79](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/79))
    * Wrote testcases for model, logic and storage, and edited necessary test utility files to account for functional developments of CodeContact, speciafically developer and client features (Pull request [\#216](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/216), [\#222](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/222), [\#236](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/236))
    * Wrote testcases for my functional contributions to codecontact. (Pull request [\#216](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/216), [\#236](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/222), [\#236](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/236))

* **Code contributed**: [RepoSense link]()


* **Project management(team-based tasks contributions)**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub


* **Documentation Contributions**:
    * User Guide Contributions:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide Contributions:
        * Added implementation details of the `delete` feature.


* **Community (Review/mentoring contributions)**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the delete feature I added was adopted by team members and class mates


* **Contributions beyond the project team:**:
    * NOTHING


* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
