package com.stephenmorgandevelopment.celero_challenge.models;

import android.util.Log;

import com.stephenmorgandevelopment.celero_challenge.utils.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final String TAG = Client.class.getSimpleName();

    private long identifier;
    private long visitOrder;
    private String name;
    private String phoneNumber;
    private ProfilePicture profilePicture;
    private Location location;
    private String serviceReason;
//    private List<String> problemPictures;
    private String[] problemPictures;

    public Client() {

    }

    public long getIdentifier() {return identifier;}
    public long getVisitOrder() {return visitOrder;}
    public String getName() {return name;}
    public String getPhoneNumber() {return phoneNumber;}
    public ProfilePicture getProfilePicture() {return profilePicture;}
    public Location getLocation() {return location;}
    public String getServiceReason() {return serviceReason;}
//    public List<String> getProblemPictures() {return problemPictures;}
    public String[] getProblemPictures() {return problemPictures;}

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public void setVisitOrder(long visitOrder) {
        this.visitOrder = visitOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setServiceReason(String serviceReason) {
        this.serviceReason = serviceReason;
    }

    public void setProblemPictures(String[] problemPictures) {
        this.problemPictures = new String[problemPictures.length];
        for(int i = 0; i < problemPictures.length; i++) {
            this.problemPictures[i] = problemPictures[i];
        }
    }

    public class ProfilePicture {
        String large;
        String medium;
        String thumbnail;

        public ProfilePicture() {

        }

        public String getLarge() {
            return large;
        }
        public String getMedium() {
            return medium;
        }
        public String getThumbnail() {
            return thumbnail;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

//        public ProfilePicture(JSONObject profilePic) {
//            try {
//                large = profilePic.getString(KEY_LARGE);
//                medium = profilePic.getString(KEY_MEDIUM);
//                thumbnail = profilePic.getString(KEY_THUMBNAIL);
//            } catch (JSONException je) {
//                Log.d(TAG, "Error parsing JSON for ProfilePicture.");
//                je.printStackTrace();
//            }
//        }

//        private String large;
//        private String medium;
//        private String thumbnail;
//
//        public ProfilePicture(String large, String medium, String thumbnail) {
//            this.large = large;
//            this.medium = medium;
//            this.thumbnail = thumbnail;
//        }
//
    }

    public class Location {
        Address address;
        Coordinate coordinate;

        public Location() {

        }

        public Address getAddress() {return address;}
        public Coordinate getCoordinate() {return coordinate;}

        public void setAddress(Address address) {
            this.address = address;
        }

        public void setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        public class Coordinate {
            double latitude;
            double longitude;

            public Coordinate() {

            }

            public double getLatitude() {return latitude;}
            public double getLongitude() {return longitude;}

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }

        public class Address {
            String street;
            String city;
            String state;
            String postalCode;
            String country;

            public Address() {

            }

            public String getStreet() {return street;}
            public String getCity() {return city;}
            public String getState() {return state;}
            public String getPostalCode() {return postalCode;}
            public String getCountry() {return country;}

            public void setStreet(String street) {
                this.street = street;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setState(String state) {
                this.state = state;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public void setCountry(String country) {
                this.country = country;
            }

        }

        //        public Location(JSONObject location) {
//            try {
//                street = location.getString(KEY_STREET);
//                city = location.getString(KEY_CITY);
//                state = location.getString(KEY_STATE);
//                postalCode = location.getString(KEY_POSTAL_CODE);
//                country = location.getString(KEY_COUNTRY);
//                latitude = location.getDouble(KEY_LATITUDE);
//                longitude = location.getDouble(KEY_LONGITUDE);
//            } catch (JSONException je) {
//                Log.d(TAG, "Error parsing JSON for Location.");
//                je.printStackTrace();
//            }
//        }
    }

//    private static final String KEY_IDENTIFIER = "identifier";
//    private static final String KEY_VISIT_ORDER = "visitOrder";
//    private static final String KEY_NAME = "name";
//    private static final String KEY_PHONE_NUMBER = "phoneNumber";
//    private static final String KEY_PROFILE_PICTURE = "profilePicture";
//    private static final String KEY_LARGE = "large";
//    private static final String KEY_MEDIUM = "medium";
//    private static final String KEY_THUMBNAIL = "thumbnail";
//    private static final String KEY_LOCATION = "location";
//    private static final String KEY_ADDRESS = "address";
//    private static final String KEY_STREET = "street";
//    private static final String KEY_CITY = "city";
//    private static final String KEY_STATE = "state";
//    private static final String KEY_POSTAL_CODE = "postalCode";
//    private static final String KEY_COUNTRY = "country";
//    private static final String KEY_COORDINATE = "coordinate";
//    private static final String KEY_LATITUDE = "latitude";
//    private static final String KEY_LONGITUDE = "longitude";
//    private static final String KEY_SERVICE_REASON = "serviceReason";
//    private static final String KEY_PROBLEM_PICTURES = "problemPictures";


//    public Client(JSONObject client) {
//        try {
//            identifier = client.getLong(KEY_IDENTIFIER);
//            visitOrder = client.getLong(KEY_VISIT_ORDER);
//            name = client.getString(KEY_NAME);
//            phoneNumber = client.getString(KEY_PHONE_NUMBER);
//            profilePicture = new ProfilePicture(client.getJSONObject(KEY_PROFILE_PICTURE));
//            location = new Location(client.getJSONObject(KEY_LOCATION));
//            serviceReason = client.getString(KEY_SERVICE_REASON);
//            problemPictures = new ArrayList<>();
//
//            JSONArray problemPicsArray = client.getJSONArray(KEY_PROBLEM_PICTURES);
//            for(int i = 0; i < problemPicsArray.length(); i++) {
//                problemPictures.add(problemPicsArray.getString(i));
//            }
//        } catch (JSONException je) {
//            Log.d(TAG, "Error parsing JSON.");
//            je.printStackTrace();
//        }
//    }
}
