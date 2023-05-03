import React, { useState, useEffect } from "react";
import axios from "axios";
import debounce from "lodash.debounce";
import { TextField } from "@mui/material";

interface SearchResult {
  name: String;
  picture: String;
  type: String;
}

const SearchBar: React.FC = () => {
  const [searchText, setSearchText] = useState("");
  const [searchResults, setSearchResults] = useState<SearchResult[]>([]);

  const fetchSearchResults = async (query: String) => {
    if (query.trim() !== "") {
      const response = await axios.get(
        `http://localhost:8081/api/search?query=${query}`
      );
      setSearchResults(response.data);
    } else {
      setSearchResults([]);
    }
  };

  const debouncedFetchSearchResults = debounce(fetchSearchResults, 300); // Adjust debounce time as needed (in milliseconds)

  useEffect(() => {
    debouncedFetchSearchResults(searchText);
  }, [searchText]);

  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchText(event.target.value);
  };

  const renderSearchResults = () => {
    return searchResults.map((result, index) => (
      <div key={index} className="search-result">
        <span className="search-result-title">{result.name} </span>
        <span className="search-result-type">{result.type}</span>
      </div>
    ));
  };

  return (
    <div>
      <TextField
        id="search-bar"
        className="text"
        onInput={handleSearchInputChange}
        value={searchText}
        variant="outlined"
        placeholder="Search..."
        size="small"
      />
      {searchText && (
        <div className="search-results">{renderSearchResults()}</div>
      )}
    </div>
  );
};

export default SearchBar;
