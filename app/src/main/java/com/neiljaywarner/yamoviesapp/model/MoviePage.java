package com.neiljaywarner.yamoviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by neil on 8/19/15.
 */
public class MoviePage implements Parcelable {
        public static final Parcelable.Creator<MoviePage> CREATOR = new Parcelable.Creator<MoviePage>() {
                public MoviePage createFromParcel(Parcel source) {
                        return new MoviePage(source);
                }

                public MoviePage[] newArray(int size) {
                        return new MoviePage[size];
                }
        };
        private int page;
        private List<YAMovie> results;  //from JSON

        public MoviePage() {
        }

        protected MoviePage(Parcel in) {
                this.page = in.readInt();
                this.results = in.createTypedArrayList(YAMovie.CREATOR);
        }

        public static MoviePage getDummyMoviePage3() {
                Gson gson = new Gson();
                String json = MoviePage.getDummyPage3();
                return gson.fromJson(json, MoviePage.class);
        }

        /**
         * NOTE:
         * <p/>
         * <p/>
         * If you ever upload your code to a public Github repo to share with other students or instructors,
         * it’s illegal to include your personal themoviedb.org API key.
         * Please remove it and note in a README where it came from, so someone else trying to run your code can create their own key and will quickly know where to put it. Instructors and code reviewers will expect this behavior for any public Github code.
         *
         * @return
         */
        private static String getDummyPage3() {
                return "{\n" +
                        "page: 3,\n" +
                        "results: [\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/6xjBbCZp3bXFps5ET1Y4zfq6bws.jpg\",\n" +
                        "genre_ids: [\n" +
                        "14,\n" +
                        "35,\n" +
                        "16,\n" +
                        "878,\n" +
                        "10751\n" +
                        "],\n" +
                        "id: 228161,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Home\",\n" +
                        "overview: \"When Earth is taken over by the overly-confident Boov, an alien race in search of a new place to call home, all humans are promptly relocated, while all Boov get busy reorganizing the planet. But when one resourceful girl, Tip (Rihanna), manages to avoid capture, she finds herself the accidental accomplice of a banished Boov named Oh (Jim Parsons). The two fugitives realize there’s a lot more at stake than intergalactic relations as they embark on the road trip of a lifetime.\",\n" +
                        "release_date: \"2015-03-27\",\n" +
                        "poster_path: \"/ieg4Mm9cqTkozxpM87ewTx5vaok.jpg\",\n" +
                        "popularity: 9.931605,\n" +
                        "title: \"Home\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.1,\n" +
                        "vote_count: 407\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/oJNu8vS6Wf10Cv2996KO65jt4C0.jpg\",\n" +
                        "genre_ids: [\n" +
                        "10752,\n" +
                        "28\n" +
                        "],\n" +
                        "id: 190859,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"American Sniper\",\n" +
                        "overview: \"U.S. Navy SEAL Chris Kyle (Bradley Cooper) takes his sole mission—protect his comrades—to heart and becomes one of the most lethal snipers in American history. His pinpoint accuracy not only saves countless lives but also makes him a prime target of insurgents. Despite grave danger and his struggle to be a good husband and father to his family back in the States, Kyle serves four tours of duty in Iraq. However, when he finally returns home, he finds that he cannot leave the war behind.\",\n" +
                        "release_date: \"2014-12-25\",\n" +
                        "poster_path: \"/svPHnYE7N5NAGO49dBmRhq0vDQ3.jpg\",\n" +
                        "popularity: 9.856386,\n" +
                        "title: \"American Sniper\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.5,\n" +
                        "vote_count: 1593\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/OqCXGt5nl1cHPeotxCDvXLLe6p.jpg\",\n" +
                        "genre_ids: [\n" +
                        "878,\n" +
                        "28,\n" +
                        "12,\n" +
                        "14,\n" +
                        "35\n" +
                        "],\n" +
                        "id: 98566,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Teenage Mutant Ninja Turtles\",\n" +
                        "overview: \"The city needs heroes. Darkness has settled over New York City as Shredder and his evil Foot Clan have an iron grip on everything from the police to the politicians. The future is grim until four unlikely outcast brothers rise from the sewers and discover their destiny as Teenage Mutant Ninja Turtles. The Turtles must work with fearless reporter April and her wise-cracking cameraman Vern Fenwick to save the city and unravel Shredder's diabolical plan.\",\n" +
                        "release_date: \"2014-08-08\",\n" +
                        "poster_path: \"/oDL2ryJ0sV2bmjgshVgJb3qzvwp.jpg\",\n" +
                        "popularity: 9.754197,\n" +
                        "title: \"Teenage Mutant Ninja Turtles\",\n" +
                        "video: false,\n" +
                        "vote_average: 6,\n" +
                        "vote_count: 1071\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/kgxidO6gSm0qB8C8QMvelmf2Wsd.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "35\n" +
                        "],\n" +
                        "id: 238713,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Spy\",\n" +
                        "overview: \"A desk-bound CIA analyst volunteers to go undercover to infiltrate the world of a deadly arms dealer, and prevent diabolical global disaster.\",\n" +
                        "release_date: \"2015-06-05\",\n" +
                        "poster_path: \"/49Akyhe0gnuokaDIKKDldFRBoru.jpg\",\n" +
                        "popularity: 9.753895,\n" +
                        "title: \"Spy\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.3,\n" +
                        "vote_count: 538\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/ai9UKd8KownQKGIh1m5p3DuEeMm.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "12,\n" +
                        "14,\n" +
                        "878\n" +
                        "],\n" +
                        "id: 127585,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"X-Men: Days of Future Past\",\n" +
                        "overview: \"The ultimate X-Men ensemble fights a war for the survival of the species across two time periods in X-MEN: DAYS OF FUTURE PAST. The beloved characters from the original “X-Men” film trilogy join forces with their younger selves from “X-Men: First Class,” in an epic battle that must change the past – to save our future.\",\n" +
                        "release_date: \"2014-05-23\",\n" +
                        "poster_path: \"/qKkFk9HELmABpcPoc1HHZGIxQ5a.jpg\",\n" +
                        "popularity: 9.718007,\n" +
                        "title: \"X-Men: Days of Future Past\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.7,\n" +
                        "vote_count: 2384\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/ts4j3zYaPzdUVF3ijBeBdGVDWjX.jpg\",\n" +
                        "genre_ids: [\n" +
                        "80,\n" +
                        "18,\n" +
                        "53\n" +
                        "],\n" +
                        "id: 242582,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Nightcrawler\",\n" +
                        "overview: \"A driven young man (Gyllenhaal) stumbles upon the underground world of L.A. freelance crime journalism. When Lou Bloom, desperate for work, muscles into the world of L.A. crime journalism, he blurs the line between observer and participant to become the star of his own story. Aiding him in his effort is Nina, a TV-news veteran.\",\n" +
                        "release_date: \"2014-10-31\",\n" +
                        "poster_path: \"/fukWJhLISH7f6cnYSdgb0JrSP2Q.jpg\",\n" +
                        "popularity: 9.339836,\n" +
                        "title: \"Nightcrawler\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.6,\n" +
                        "vote_count: 1101\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/8dHsvdiZLBdppKwRiZ0XZYngbeN.jpg\",\n" +
                        "genre_ids: [\n" +
                        "35\n" +
                        "],\n" +
                        "id: 257091,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Get Hard\",\n" +
                        "overview: \"When obscenely rich hedge-fund manager James is convicted of fraud and sentenced to a stretch in San Quentin, the judge gives him one month to get his affairs in order. Knowing that he won't survive more than a few minutes in prison on his own, James desperately turns to Darnell-- a black businessman who's never even had a parking ticket -- for help. As Darnell puts James through the wringer, both learn that they were wrong about many things, including each other.\",\n" +
                        "release_date: \"2015-03-27\",\n" +
                        "poster_path: \"/qRzUSrN4p6B7fzA5XGm4ebFg3co.jpg\",\n" +
                        "popularity: 9.131378,\n" +
                        "title: \"Get Hard\",\n" +
                        "video: false,\n" +
                        "vote_average: 6,\n" +
                        "vote_count: 278\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/9X3cDZb4GYGQeOnZHLwMcCFz2Ro.jpg\",\n" +
                        "genre_ids: [\n" +
                        "18,\n" +
                        "878\n" +
                        "],\n" +
                        "id: 264660,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Ex Machina\",\n" +
                        "overview: \"Caleb, a 24 year old coder at the world's largest internet company, wins a competition to spend a week at a private mountain retreat belonging to Nathan, the reclusive CEO of the company. But when Caleb arrives at the remote location he finds that he will have to participate in a strange and fascinating experiment in which he must interact with the world's first true artificial intelligence, housed in the body of a beautiful robot girl.\",\n" +
                        "release_date: \"2015-01-21\",\n" +
                        "poster_path: \"/btbRB7BrD887j5NrvjxceRDmaot.jpg\",\n" +
                        "popularity: 9.005307,\n" +
                        "title: \"Ex Machina\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.6,\n" +
                        "vote_count: 912\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/A2WK7mdiKHaxgE4hRyKuDIM4KGg.jpg\",\n" +
                        "genre_ids: [\n" +
                        "53,\n" +
                        "18\n" +
                        "],\n" +
                        "id: 181283,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Child 44\",\n" +
                        "overview: \"Set in Stalin-era Soviet Union, a disgraced MGB agent is dispatched to investigate a series of child murders -- a case that begins to connect with the very top of party leadership.\",\n" +
                        "release_date: \"2015-04-17\",\n" +
                        "poster_path: \"/nzXwfnBcsmREzHlem6fkStN1RUH.jpg\",\n" +
                        "popularity: 8.934827,\n" +
                        "title: \"Child 44\",\n" +
                        "video: false,\n" +
                        "vote_average: 5.9,\n" +
                        "vote_count: 108\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/mQwpjcFzQpZOZWqyyNKsdhjDCxF.jpg\",\n" +
                        "genre_ids: [\n" +
                        "18,\n" +
                        "10402\n" +
                        "],\n" +
                        "id: 277216,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Straight Outta Compton\",\n" +
                        "overview: \"The group NWA emerges from the streets of Compton, California in the mid-1980s and revolutionizes pop culture with their music and tales about life in the hood.\",\n" +
                        "release_date: \"2015-08-14\",\n" +
                        "poster_path: \"/X7S1RtotXOZNV7OlgCfh5VKZSB.jpg\",\n" +
                        "popularity: 7.905834,\n" +
                        "title: \"Straight Outta Compton\",\n" +
                        "video: false,\n" +
                        "vote_average: 8.1,\n" +
                        "vote_count: 45\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/5VzeN1imymqZu6YAO9bNYdJ9Gde.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "18\n" +
                        "],\n" +
                        "id: 307081,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Southpaw\",\n" +
                        "overview: \"A boxer fights his way to the top, only to find his life falling apart around him.\",\n" +
                        "release_date: \"2015-07-24\",\n" +
                        "poster_path: \"/qFC07nj9lWWmnbkS191AgFUth9J.jpg\",\n" +
                        "popularity: 8.846106,\n" +
                        "title: \"Southpaw\",\n" +
                        "video: false,\n" +
                        "vote_average: 6.7,\n" +
                        "vote_count: 95\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/sfJNjSGBEmc3gFhOtHNyq94N7Of.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "35\n" +
                        "],\n" +
                        "id: 268920,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Hot Pursuit\",\n" +
                        "overview: \"An uptight by-the-book cop must protect the widow of a drug boss from crooked cops and gunmen.\",\n" +
                        "release_date: \"2015-05-08\",\n" +
                        "poster_path: \"/rQSebx3Ie1TL12TbHwFoVqRMU5r.jpg\",\n" +
                        "popularity: 8.630747,\n" +
                        "title: \"Hot Pursuit\",\n" +
                        "video: false,\n" +
                        "vote_average: 5.6,\n" +
                        "vote_count: 132\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/eCgIoGvfNXrbSiQGqQHccuHjQHm.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "878\n" +
                        "],\n" +
                        "id: 240832,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Lucy\",\n" +
                        "overview: \"A woman, accidentally caught in a dark deal, turns the tables on her captors and transforms into a merciless warrior evolved beyond human logic.\",\n" +
                        "release_date: \"2014-07-25\",\n" +
                        "poster_path: \"/rwn876MeqienhOVSSjtUPnwxn0Z.jpg\",\n" +
                        "popularity: 8.575148,\n" +
                        "title: \"Lucy\",\n" +
                        "video: false,\n" +
                        "vote_average: 6.3,\n" +
                        "vote_count: 2243\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/3qlzyunDrkwWOKEWZRxeIF4HEUC.jpg\",\n" +
                        "genre_ids: [\n" +
                        "878,\n" +
                        "28,\n" +
                        "12\n" +
                        "],\n" +
                        "id: 1771,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Captain America: The First Avenger\",\n" +
                        "overview: \"Predominantly set during World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.\",\n" +
                        "release_date: \"2011-07-22\",\n" +
                        "poster_path: \"/sBZs1jSybBRBXDwcCR8IOyHLUMc.jpg\",\n" +
                        "popularity: 8.517462,\n" +
                        "title: \"Captain America: The First Avenger\",\n" +
                        "video: false,\n" +
                        "vote_average: 6.4,\n" +
                        "vote_count: 3706\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/73hI1zgRALUDrB46JDPBLFqHcqv.jpg\",\n" +
                        "genre_ids: [\n" +
                        "12,\n" +
                        "35,\n" +
                        "14,\n" +
                        "10751\n" +
                        "],\n" +
                        "id: 181533,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Night at the Museum: Secret of the Tomb\",\n" +
                        "overview: \"When the magic powers of The Tablet of Ahkmenrah begin to die out, Larry Daley (Ben Stiller) spans the globe, uniting favorite and new characters while embarking on an epic quest to save the magic before it is gone forever.\",\n" +
                        "release_date: \"2014-12-19\",\n" +
                        "poster_path: \"/tWwASv4CU1Au1IukacdSUewDCV3.jpg\",\n" +
                        "popularity: 7.418959,\n" +
                        "title: \"Night at the Museum: Secret of the Tomb\",\n" +
                        "video: false,\n" +
                        "vote_average: 6.2,\n" +
                        "vote_count: 506\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/hJGqSanlHxPOHXnTQk5zsfuXoJN.jpg\",\n" +
                        "genre_ids: [\n" +
                        "53,\n" +
                        "80,\n" +
                        "18\n" +
                        "],\n" +
                        "id: 265208,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Wild Card\",\n" +
                        "overview: \"When a Las Vegas bodyguard with lethal skills and a gambling problem gets in trouble with the mob, he has one last play… and it's all or nothing.\",\n" +
                        "release_date: \"2015-01-30\",\n" +
                        "poster_path: \"/cZMjg5fWkhJ1D7MouzZuiB16Hdk.jpg\",\n" +
                        "popularity: 8.322119,\n" +
                        "title: \"Wild Card\",\n" +
                        "video: false,\n" +
                        "vote_average: 5.4,\n" +
                        "vote_count: 263\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/mFb0ygcue4ITixDkdr7wm1Tdarx.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "53\n" +
                        "],\n" +
                        "id: 245891,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"John Wick\",\n" +
                        "overview: \"After the sudden death of his beloved wife, John Wick receives one last gift from her, a beagle puppy named Daisy, and a note imploring him not to forget how to love. But John's mourning is interrupted when his 1969 Boss Mustang catches the eye of sadistic thug Iosef Tarasov who breaks into his house and steals it, beating John unconscious in the process. Unwittingly, he has just reawakened one of the most brutal assassins the underworld has ever known.\",\n" +
                        "release_date: \"2014-10-24\",\n" +
                        "poster_path: \"/sq2MmFv9sanl9PFMfbdaBLveSJ8.jpg\",\n" +
                        "popularity: 8.244235,\n" +
                        "title: \"John Wick\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.2,\n" +
                        "vote_count: 1183\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/kEMT6qGmrEqzN2JkYN45oRQuwWZ.jpg\",\n" +
                        "genre_ids: [\n" +
                        "53,\n" +
                        "18,\n" +
                        "9648\n" +
                        "],\n" +
                        "id: 182560,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Dark Places\",\n" +
                        "overview: \"A woman who survived the brutal killing of her family as a child is forced to confront the events of that day.\",\n" +
                        "release_date: \"2015-06-17\",\n" +
                        "poster_path: \"/1z7Bxnxi1lgO0ksc6peI4UssEPf.jpg\",\n" +
                        "popularity: 7.987754,\n" +
                        "title: \"Dark Places\",\n" +
                        "video: false,\n" +
                        "vote_average: 4.7,\n" +
                        "vote_count: 77\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/4qfXT9BtxeFuamR4F49m2mpKQI1.jpg\",\n" +
                        "genre_ids: [\n" +
                        "28,\n" +
                        "12,\n" +
                        "878\n" +
                        "],\n" +
                        "id: 100402,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Captain America: The Winter Soldier\",\n" +
                        "overview: \"After the cataclysmic events in New York with The Avengers, Steve Rogers, aka Captain America is living quietly in Washington, D.C. and trying to adjust to the modern world. But when a S.H.I.E.L.D. colleague comes under attack, Steve becomes embroiled in a web of intrigue that threatens to put the world at risk. Joining forces with the Black Widow, Captain America struggles to expose the ever-widening conspiracy while fighting off professional assassins sent to silence him at every turn. When the full scope of the villainous plot is revealed, Captain America and the Black Widow enlist the help of a new ally, the Falcon. However, they soon find themselves up against an unexpected and formidable enemy—the Winter Soldier.\",\n" +
                        "release_date: \"2014-04-04\",\n" +
                        "poster_path: \"/5TQ6YDmymBpnF005OyoB7ohZps9.jpg\",\n" +
                        "popularity: 7.751485,\n" +
                        "title: \"Captain America: The Winter Soldier\",\n" +
                        "video: false,\n" +
                        "vote_average: 7.7,\n" +
                        "vote_count: 2252\n" +
                        "},\n" +
                        "{\n" +
                        "adult: false,\n" +
                        "backdrop_path: \"/6UPlIYKxZqUR6Xbpgu1JKG0J7UC.jpg\",\n" +
                        "genre_ids: [\n" +
                        "27,\n" +
                        "28,\n" +
                        "18,\n" +
                        "14,\n" +
                        "10752\n" +
                        "],\n" +
                        "id: 49017,\n" +
                        "original_language: \"en\",\n" +
                        "original_title: \"Dracula Untold\",\n" +
                        "overview: \"Vlad Tepes is a great hero, but when he learns the Sultan is preparing for battle and needs to form an army of 1,000 boys, including Vlad's son, he vows to find a way to protect his family. Vlad turns to dark forces in order to get the power to destroy his enemies and agrees to go from hero to monster as he's turned into the mythological vampire Dracula.\",\n" +
                        "release_date: \"2014-10-03\",\n" +
                        "poster_path: \"/4oy4e0DP6LRwRszfx8NY8EYBj8V.jpg\",\n" +
                        "popularity: 7.706917,\n" +
                        "title: \"Dracula Untold\",\n" +
                        "video: false,\n" +
                        "vote_average: 6.2,\n" +
                        "vote_count: 1027\n" +
                        "}\n" +
                        "],\n" +
                        "total_pages: 11958,\n" +
                        "total_results: 239153\n" +
                        "}";

        }

        public List<YAMovie> getMovies() {
                return results;
        }

        /**
         * Convenience method to return the nth movie
         *
         * @param n
         * @return
         */
        public YAMovie getMovie(int n) {
                return getMovies().get(n);
        }

        public int getPage() {
                return page;
        }

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.page);
                dest.writeTypedList(results);
        }
}
