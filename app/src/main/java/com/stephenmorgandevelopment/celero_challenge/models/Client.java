package com.stephenmorgandevelopment.celero_challenge.models;

public class Client {
    private static final String TAG = Client.class.getSimpleName();

    private long identifier;
    private long visitOrder;
    private String name;
    private String phoneNumber;
    private ProfilePicture profilePicture;
    private Location location;
    private String serviceReason;
    private String[] problemPictures;

    public Client() {

    }

    public long getIdentifier() {
        return identifier;
    }

    public long getVisitOrder() {
        return visitOrder;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public Location getLocation() {
        return location;
    }

    public String getServiceReason() {
        return serviceReason;
    }

    public String[] getProblemPictures() {
        return problemPictures;
    }

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
        for (int i = 0; i < problemPictures.length; i++) {
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
    }

    public class Location {
        Address address;
        Coordinate coordinate;

        public Location() {

        }

        public Address getAddress() {
            return address;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

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

            public double getLatitude() {
                return latitude;
            }

            public double getLongitude() {
                return longitude;
            }

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

            public String getStreet() {
                return street;
            }

            public String getCity() {
                return city;
            }

            public String getState() {
                return state;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public String getCountry() {
                return country;
            }

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

    }

}
