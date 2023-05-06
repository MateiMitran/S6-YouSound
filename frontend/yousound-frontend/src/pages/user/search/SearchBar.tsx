import React, { useState, useEffect } from "react";
import axios from "axios";
import debounce from "lodash.debounce";
import { Paper, IconButton, Divider, InputBase } from "@mui/material";
import { Search } from "@mui/icons-material";

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
      <Paper
        component="form"
        sx={{
          p: "2px 4px",
          display: "flex",
          alignItems: "center",
          width: 400,
          borderRadius: "15px",
          border: "3px solid #4745A0",
        }}
      >
        <IconButton sx={{ p: "10px" }} aria-label="menu">
          <Search />
        </IconButton>
        <Divider sx={{ height: 28, m: 0.5 }} orientation="vertical" />
        <InputBase
          sx={{ ml: 1, flex: 1 }}
          placeholder="What do you want to listen to...?"
          inputProps={{ "aria-label": "search" }}
          onInput={handleSearchInputChange}
          value={searchText}
        />
      </Paper>
      {searchText && (
        <div className="search-results">{renderSearchResults()}</div>
      )}
    </div>
  );
};

export default SearchBar;
