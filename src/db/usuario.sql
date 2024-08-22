CREATE TABLE IF NOT EXISTS usuario (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        nome TEXT NOT NULL,
                                        email TEXT NOT NULL UNIQUE,
                                        senha TEXT NOT NULL
);
