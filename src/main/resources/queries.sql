
-- Query 1: Retrieve all currencies from the database
SELECT * FROM currencies;

-- Query 2: Retrieve the currency with the abbreviation 'EUR'
SELECT * FROM currencies WHERE abbreviation = 'EUR';

-- Query 3: Retrieve the number of currencies in the database
SELECT COUNT(*) AS currency_count FROM currencies;

-- Query 4: Retrieve the currency with the highest conversion rate
SELECT * FROM currencies ORDER BY conversion_rate DESC LIMIT 1;
