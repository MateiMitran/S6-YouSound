import * as React from "react";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { Box, Button, Stack, styled } from "@mui/material";
import { MusicService } from "../../../services/MusicService";
import { ContentEntity } from "../../../types";

const columns: GridColDef[] = [
  { field: "name", headerName: "Name", width: 130 },
  { field: "artist_id", headerName: "Artist Name", width: 200 },
];

const columnsP: GridColDef[] = [
  { field: "name", headerName: "Name", width: 130 },
  { field: "creator_id", headerName: "User ID", width: 130 },
  { field: "nr_of_songs", headerName: "Number of Songs", width: 200},
  { field: "likes", headerName: "Likes", width: 130}
];


const CssDataGrid = styled(DataGrid)({
  "& .MuiDataGrid-cell": {
    color: "white",
  },
  "& .MuiDataGrid-columnHeaderTitle": {
    color: "white",
  },
  "& .MuiDataGrid-footerContainer": {
    color: "white",
  },
});

export const Music: React.FC = () => {
  const [content, setContent] = React.useState<ContentEntity>();
  const [selectedSongRows, setSelectedSongRows] = React.useState<any>([]);
  const [selectedAlbumRows, setSelectedAlbumRows] = React.useState<any>([]);
  const [selectedPlaylistRows, setSelectedPlaylistRows] = React.useState<any>(
    []
  );
  const [paginationS, setPaginationS] = React.useState<number>(1);
  const [paginationA, setPaginationA] = React.useState<number>(1);
  const [paginationP, setPaginationP] = React.useState<number>(1);

  const handlePaginationS = () => {
    if (paginationS === 3) {
      setPaginationS(1);
    } else {
      setPaginationS(paginationS + 1);
    }
  };

  const handlePaginationA = () => {
    if (paginationA === 3) {
      setPaginationA(1);
    } else {
      setPaginationA(paginationA + 1);
    }
  };

  const handlePaginationP = () => {
    if (paginationP === 3) {
      setPaginationP(1);
    } else {
      setPaginationS(paginationP + 1);
    }
  };

  const handleSelectionSongModelChange = (selectionModel: any) => {
    setSelectedSongRows(selectionModel);
  };

  const deleteSelectedSongRows = async (selectedRows: any) => {
    for (const id of selectedRows) {
      try {
        await MusicService.deleteSongById(id);
      } catch (error) {
        console.error(`Error deleting song with id ${id}: ${error}`);
      }
    }
  };

  const handleDeleteButtonClick = async (i: number) => {
    if (i === 1) {
      deleteSelectedSongRows(selectedSongRows);
    }
    if (i === 2) {
      deleteSelectedAlbumRows(selectedAlbumRows);
    }
    if (i === 3) {
      deleteSelectedPlaylistRows(selectedPlaylistRows);
    }
  };

  const handleSelectionAlbumModelChange = (selectionModel: any) => {
    setSelectedAlbumRows(selectionModel);
  };

  const deleteSelectedAlbumRows = async (selectedRows: any) => {
    for (const id of selectedRows) {
      try {
        await MusicService.deleteAlbumById(id);
      } catch (error) {
        console.error(`Error deleting album with id ${id}: ${error}`);
      }
    }
  };

  const handleSelectionPlaylistModelChange = (selectionModel: any) => {
    setSelectedPlaylistRows(selectionModel);
  };

  const deleteSelectedPlaylistRows = async (selectedRows: any) => {
    for (const id of selectedRows) {
      try {
        await MusicService.deletePlaylistById(id);
      } catch (error) {
        console.error(`Error deleting playlist with id ${id}: ${error}`);
      }
    }
  };

  const fetchContent = async () => {
    await MusicService.getContent().then((res) => {
      setContent(res);
    });
  };

  React.useEffect(() => {
    fetchContent();
  }, [content]);

  React.useEffect(() => {
    localStorage.removeItem("token");
    fetchContent();
  }, []);

  return (
    <Box style={{ height: 400, width: "100%" }}>
      <Stack direction="row" spacing={2} sx={{ mb: 2 }}>
        {content?.songs && (
          <CssDataGrid
            rows={content.songs}
            columns={columns}
            checkboxSelection
            onRowSelectionModelChange={handleSelectionSongModelChange}
            rowSelectionModel={selectedSongRows}
            paginationModel={{ page: paginationS, pageSize: 10 }}
            onPaginationModelChange={handlePaginationS}
          />
        )}
        {content?.albums && (
          <CssDataGrid
            rows={content.albums}
            columns={columns}
            checkboxSelection
            onRowSelectionModelChange={handleSelectionAlbumModelChange}
            rowSelectionModel={selectedAlbumRows}
            paginationModel={{ page: paginationA, pageSize: 10 }}
            onPaginationModelChange={handlePaginationA}
          />
        )}
        {content?.playlists && (
          <CssDataGrid
            rows={content.playlists}
            columns={columnsP}
            checkboxSelection
            onRowSelectionModelChange={handleSelectionPlaylistModelChange}
            rowSelectionModel={selectedPlaylistRows}
            paginationModel={{ page: paginationP, pageSize: 10 }}
            onPaginationModelChange={handlePaginationP}
          />
        )}
      </Stack>
      {selectedSongRows.length > 0 && (
        <Button variant="contained" sx={{mr: "1rem"}} onClick={() => handleDeleteButtonClick(1)}>
          Delete Songs
        </Button>
      )}
      {selectedAlbumRows.length > 0 && (
        <Button variant="contained" sx={{ml: "1rem", mr: "1rem"}} onClick={() => handleDeleteButtonClick(2)}>
          Delete Albums
        </Button>
      )}
      {selectedPlaylistRows.length > 0 && (
        <Button variant="contained" sx={{ml: "1rem", mr: "1rem"}} onClick={() => handleDeleteButtonClick(3)}>
          Delete Playlists
        </Button>
      )}
    </Box>
  );
};
