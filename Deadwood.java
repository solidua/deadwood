import java.util.*;
import java.lang.*;
import java.io.*;
class Deadwood{
    public static void main(String[] args) throws FileNotFoundException{
        File cardFile = new File("cards.txt");
        Scanner darkly = new Scanner(cardFile);
        Card[] cardArray = new Card[40];
        int i = 0;
        while (darkly.hasNext()){
            String cardLine = darkly.nextLine();
            Scanner cardReader = new Scanner(cardLine);
            cardReader.useDelimiter(", ");
            String title = cardReader.next();
            String description = cardReader.next();
            int levelOne = cardReader.nextInt();
            String nameOne = cardReader.next();
            String phraseOne = cardReader.next();
            int levelTwo = cardReader.nextInt();
            String nameTwo = cardReader.next();
            String phraseTwo = cardReader.next();
            int levelThree = cardReader.nextInt();
            String nameThree = cardReader.next();
            String phraseThree = cardReader.next();
            int budget = cardReader.nextInt();
            Role roleOne = new Role(nameOne, levelOne, phraseOne);
            Role roleTwo = new Role(nameTwo, levelTwo, phraseTwo);
            Role roleThree = new Role(nameThree, levelThree, phraseThree);
            cardArray[i] = new Card(budget, roleOne, roleTwo, roleThree, title, description);
            i++;
        }
        SceneRoom mainStreet = new SceneRoom("Main Street", "Scene Room", 3, new Role("Railroad Worker", 1, "I'm a steel-drivin' man!"), new Role("Falls off Roof", 2, "Aaaaaaaaiiigggghh!"), new Role("Woman in Black Dress", 2, "Well, I'll be!"), new Role("Mayor McGinty", 4, "People of Deadwood!"));
        SceneRoom saloon = new SceneRoom("Saloon", "Scene Room", 2, new Role("Woman in Red Dress", 2, "Come up and see me!"), new Role("Reluctant Farmer", 1, "I ain't so sure about that!"), new Role(null, 0, null), new Role(null, 0, null));
        SceneRoom ranch = new SceneRoom("Ranch", "Scene Room", 2, new Role("Shot in Leg", 1, "Ow! Me Leg!"), new Role("Man Under Horse", 3, "A little help here!"), new Role("Saucy Fred", 2, "That's what she said!"), new Role(null, 0, null));
        SceneRoom secretHideout = new SceneRoom("Secret Hideout", "Scene Room", 3, new Role("Clumsy Pit Fighter", 1, "Hit me!"), new Role("Thug with Knife", 2, "Meet Suzy, my murderin' knife."), new Role("Dangerous Tom", 3, "There's two ways we can do this"), new Role("Penny, who is Lost", 4, "Oh, woe! For I am lost!"));
        SceneRoom bank = new SceneRoom("Bank", "Scene Room", 1, new Role("Flustered Teller", 3, "Would you like a large bill, sir?"), new Role("Suspicious Gentleman", 2, "Can you be more specific?"), new Role(null, 0, null), new Role(null, 0, null));
        SceneRoom hotel = new SceneRoom("Hotel", "Scene Room", 3, new Role("Faro Player", 1, "Hit me!"), new Role("Sleeping Drunkard", 1, "Zzzzz... Whiskey!"), new Role("Australian Bartender", 3, "What'll it be, mate?"), new Role("Falls from Balcony", 2, "Arrrgghh!!"));
        SceneRoom church = new SceneRoom("Church", "Scene Room", 2, new Role("Dead Man", 1, "..."), new Role("Crying Woman", 2, "Oh, the humanity!"), new Role(null, 0, null), new Role(null, 0, null));
        SceneRoom trainStation = new SceneRoom("Train Station", "Scene Room", 3, new Role("Dragged by Train", 1, "Omgeezers!"), new Role("Crusty Propector", 1, "Aww, peaches!"), new Role("Cyrus the Gunfighter", 4, "Git to fightin' or git away"), new Role("Preacher with Bag", 2, "The Lord will provide."));
        SceneRoom jail = new SceneRoom("Jail", "Scene Room", 1, new Role("Prisoner in Cell", 2, "Zzzzz... Whiskey!"), new Role("Feller in Irons", 3, "Ah kilt the wrong man!"), new Role(null, 0, null), new Role(null, 0, null));
        SceneRoom generalStore = new SceneRoom("General Store", "Scene Room", 2, new Role("Man in Overalls", 1, "Looks like a storm's comin' in."), new Role("Mister Keach", 3, "Howdy, stranger."), new Role(null, 0, null), new Role(null, 0, null));
        UtilityScene trailers = new UtilityScene("Trailers");
        UtilityScene castingOffice = new UtilityScene("Casting Office");
        Room[] adjacent = {trailers, saloon, jail};
        mainStreet.setAdjacentRooms(adjacent);
        adjacent = new Room[] {mainStreet, saloon, hotel};
        trailers.setAdjacentRooms(adjacent);
        adjacent = new Room[] {mainStreet, trailers, bank, generalStore};
        saloon.setAdjacentRooms(adjacent);
        adjacent = new Room[] {ranch, secretHideout, trainStation};
        castingOffice.setAdjacentRooms(adjacent);
        adjacent = new Room[] {castingOffice, ranch, church};
        secretHideout.setAdjacentRooms(adjacent);
        adjacent = new Room[] {castingOffice, secretHideout, bank, generalStore};
        ranch.setAdjacentRooms(adjacent);
        adjacent = new Room[] {hotel, church, ranch, saloon};
        bank.setAdjacentRooms(adjacent);
        adjacent = new Room[] {bank, hotel, secretHideout};
        church.setAdjacentRooms(adjacent);
        adjacent = new Room[] {bank, church, trailers};
        hotel.setAdjacentRooms(adjacent);
        adjacent = new Room[] {mainStreet, generalStore, trainStation};
        jail.setAdjacentRooms(adjacent);
        adjacent = new Room[] {jail, generalStore, castingOffice};
        trainStation.setAdjacentRooms(adjacent);
        adjacent = new Room[] {jail, trainStation, ranch};
        generalStore.setAdjacentRooms(adjacent);
    }
}