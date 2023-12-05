package org.branch_code;

import java.util.Random;

public class UserNameGenerator {
    /**
     * this generates a random username
     *
     * @param somePseudoName it shall be used in username if provided
     * @return
     */
    static String getUserText(String somePseudoName) {
        String[] nameArray = new String[]{
                "Wallace Paucek",
                "Treasa Mohr",
                "Dean Zemlak",
                "Kraig Collier",
                "Mr. Evelin Schuster",
                "Rachelle Bartoletti",
                "Mr. Alfonso Cummerata",
                "Bryce Prohaska",
                "Kareen Douglas",
                "Jennette Jacobs",
                "Darnell Bauch",
                "Luciano Marvin",
                "Lakiesha Moore",
                "Soon Dietrich",
                "Gia Bruen",
                "Eunice Kassulke II",
                "Leandro Blanda",
                "Mr. Kathline Pouros",
                "Mathew Leuschke",
                "Joshua Fahey",
                "Patrina Bergnaum",
                "Joanna Labadie",
                "Shelby Hartmann",
                "Alena Miller",
                "Terry Kunde",
                "Aubrey Gaylord",
                "Bernardina Spencer",
                "Miss Camie Klocko",
                "Ms. Bernadette Reichel",
                "Wm Schmitt",
                "Mr. Dalton Ritchie",
                "Israel Lubowitz V",
                "Efren O'Hara",
                "Kyla Dickens",
                "Dr. Raymundo McDermott",
                "Dalton Yost",
                "Dr. Dennis Goodwin",
                "Yulanda Bosco",
                "Hiram Lemke",
                "Georgeann Gleason",
                "Denese Stroman",
                "Herminia McLaughlin",
                "Eldon Kulas PhD",
                "Darrel Kassulke Jr.",
                "Chanda Klocko",
                "Dionna Kulas",
                "Mrs. Ethan Gerlach",
                "Karina Corkery",
                "Ms. Nigel Bartell",
                "Ms. Elvina Marks",
                "Felicidad Wehner DVM",
                "Adele McGlynn",
                "Graciela Kutch I",
                "Danyell Okuneva",
                "Carmon Hoppe Jr.",
                "Vito Daugherty",
                "Reid Farrell",
                "Newton Vandervort",
                "Antoine Durgan",
                "Jeneva Zulauf",
                "Jonathon Quigley",
                "Markus Effertz",
                "Vicenta Jones",
                "Stacy Weissnat",
                "Dorthea Tillman II",
                "Thomas Nader",
                "Keith Mohr",
                "Miquel Gleason",
                "Darron Halvorson",
                "Darrell Koch",
                "Micki Lind",
                "Laurine Leannon",
                "Mr. Shirleen Hilpert",
                "Stevie Schimmel",
                "Delinda Wisoky",
                "Graig Mohr",
                "Phyliss Jerde",
                "Teofila Kirlin V",
                "Celestine Mohr IV",
                "Phil Gaylord DDS",
                "Mckinley Metz DDS",
                "Esther Will",
                "Cole Kreiger",
                "Karmen Rodriguez",
                "Mr. Willy Reichert",
                "Shira Vandervort",
                "Mrs. Thomas MacGyver",
                "Tyree Fadel",
                "Ira Langq",
                "Mariano Price Sr.",
                "Mr. Adan Halvorson",
                "Mrs. Newton Farrell",
                "Chase Friesen",
                "Bobbye Hudson",
                "Abbie Price DVM",
                "Josephina Tromp I",
                "Monserrate Batz",
                "Anette Aufderhar Jr",
                "Blaine Botsford",
                "Wallace Von",
                "Miss Ricky Quigley",
                "Livia Rath",
                "Dr. Conrad D'Amore",
                "Keven Hansen",
                "Jon Welch",
                "Stephen Hilll",
                "Mr. Hulda Bergnaum",
                "Annett Roob:PObT1QrGCX",
                "Jacquie Stokes",
                "Marquita White",
                "Murray Turner",
                "Romeo Graham",
                "Bernie Lehner",
                "Ariel Pollich",
                "Elizabet Sauer",
                "Gregory Gorczany",
                "Sharon Bergstrom IV",
                "Gladis Braun",
                "Belen Schaefer",
                "Pandora Gleichner",
                "Ricardo Gibson",
                "Mr. Homer O'Keefe",
                "Mr. Irene Conroy",
                "Talia Romaguera DDS",
                "Werner Kassulke",
                "Miss Charita Deckow",
                "Enedina Steuber",
                "Stevie Zemlak",
                "Miss Noah Brekke",
                "Mr. Ola Ratke",
                "Billi Treutel",
                "Philip Mills",
                "Marissa Bernier",
                "Colin Runolfsdottir",
                "Tuan Wiza",
                "Mrs. Tyson Waters",
                "Max Gerhold",
                "Ms. Jeremiah Barton",
                "Mr. Everett Stoltenberg",
                "Neal Schiller",
                "Lovella Roberts",
                "Mr. Lucien Koss",
                "Barrett King I",
                "Miss Kenny Dicki",
                "Hector Lueilwitz",
                "Deana Mayer",
                "Roselle Luettgen DVM",
                "Dr. Tammara Vandervort",
                "Russel McDermott",
                "Tomi Reinger",
                "Neil Schroeder",
                "Jim Haag",
                "Florencia Mohr",
                "Shayne Kuphal",
                "Alphonse Pouros",
                "Jane Rolfson",
                "Tim Kuhic Jr.",
                "Lucas Rodriguez III",
                "Earnest Kohler",
                "Tawana Stark",
                "Ms. Vern Kilback",
                "Stacie Balistreri MD",
                "Manuela Hirthe",
                "Annita Russel",
                "Darrick Lemke",
                "Reed Durgan",
                "Emerson Smitham I",
                "Percy Roberts III",
                "Wesley Wisozk:2mMosjFK3a:"
        };
        String userName = "";
        if (somePseudoName != null && somePseudoName.length() > 0) {
            userName = somePseudoName;
        } else {
            userName = nameArray[new Random().nextInt(nameArray.length)];
        }
        return userName + getRandomNumber();
    }

    /**
     * this shall create a random number
     *
     * @return a number text
     */
    private static String getRandomNumber() {
        StringBuilder numberText = new StringBuilder();
        int[] numbersArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int totalNumbers = new Random().nextInt(3);

        for (int index = 0; index < totalNumbers; index++) {
            numberText.append(numbersArray[new Random().nextInt(numbersArray.length)]);
        }
        return numberText.toString();
    }
}
