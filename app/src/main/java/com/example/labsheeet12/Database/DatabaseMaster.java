package com.example.labsheeet12.Database;

import android.provider.BaseColumns;

public final class DatabaseMaster {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private DatabaseMaster() {}

        /* Inner class that defines the table contents */
        public static class User implements BaseColumns {
            public static final String TABLE_NAME = "users";
            public static final String COL1 = "Name";
            public static final String COL2 = "Password";
            public static final String COL3 = "Type";
        }

        public static class Message implements BaseColumns {
            public static final String TABLE_NAME = "message";
            public static final String COL1 = "User";
            public static final String COL2 = "Subject";
            public static final String COL3 = "Message";
    }

}

