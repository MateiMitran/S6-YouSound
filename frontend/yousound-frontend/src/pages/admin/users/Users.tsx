import * as React from "react";
import { UserService } from "../../../services/UserService";
import { DataGrid, GridColDef, GridValueGetterParams } from "@mui/x-data-grid";
import { Button, styled } from "@mui/material";
import { UserEntity } from "../../../types";

const columns: GridColDef[] = [
  { field: "username", headerName: "Username", width: 130 },
  { field: "firstName", headerName: "First name", width: 130 },
  { field: "lastName", headerName: "Last name", width: 130 },
  {
    field: "email",
    headerName: "Email",
    width: 200,
  },
  {
    field: "fullName",
    headerName: "Full name",
    description: "This column has a value getter and is not sortable.",
    sortable: false,
    width: 160,
    valueGetter: (params: GridValueGetterParams) =>
      `${params.row.firstName || ""} ${params.row.lastName || ""}`,
  },
  {
    field: "verified",
    headerName: "Verified",
    width: 100,
  },
];

const CssDataGrid = styled(DataGrid)({
  "& .MuiDataGrid-columnHeaderTitle": {
    color: "white",
  },

  "& .MuiDataGrid-footerContainer": {
    color: "white",
  },
});

export const Users: React.FC = () => {
  const [users, setUsers] = React.useState<UserEntity[]>([]);
  const [selectedRows, setSelectedRows] = React.useState<any>([]);

  const handleSelectionModelChange = (selectionModel: any) => {
    setSelectedRows(selectionModel);
  };

  const deleteSelectedRows = async (selectedRows: any) => {
    for (const id of selectedRows) {
      try {
        await UserService.deleteById(id);
      } catch (error) {
        console.error(`Error deleting user with id ${id}: ${error}`);
      }
    }
  };

  const handleDeleteButtonClick = async () => {
    deleteSelectedRows(selectedRows);
    window.location.reload();
  };

  const fetchUsers = async () => {
    await UserService.getAllUsers()
      .then((res) => {
        setUsers(res);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  React.useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div style={{ height: 400, width: "100%" }}>
      <CssDataGrid
        rows={users}
        columns={columns}
        checkboxSelection
        onRowSelectionModelChange={handleSelectionModelChange}
        rowSelectionModel={selectedRows}
      />
      {selectedRows.length > 0 && (
        <Button variant="contained" onClick={handleDeleteButtonClick}>
          Delete selection
        </Button>
      )}
    </div>
  );
};
