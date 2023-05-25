import {
  Box,
  Button,
  Step,
  StepLabel,
  Stepper,
  TextField,
  Typography,
} from "@mui/material";
import { DatePicker, DateValidationError } from "@mui/x-date-pickers";
import { PickerChangeHandlerContext } from "@mui/x-date-pickers/internals/hooks/usePicker/usePickerValue.types";
import * as React from "react";
import { Dayjs } from "dayjs";
import { MusicService } from "../../../services/MusicService";
import { MuiFileInput } from "mui-file-input";

const steps = ["Song details", "MP3 File and Picture"];

export const ArtistHome: React.FC = () => {
  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set<number>());
  const [created_at, setCreatedAt] = React.useState<string>("");
  const [name, setName] = React.useState<string>("");
  const [description, setDescription] = React.useState<string>("");
  const [genre, setGenre] = React.useState<string>("");

  const [songId, setSongId] = React.useState<number | undefined>(0);

  const [picture, setPicture] = React.useState<any>(null);
  const [file, setFile] = React.useState<any>(null);

  const isStepOptional = (step: number) => {
    return step === 1;
  };

  const isStepSkipped = (step: number) => {
    return skipped.has(step);
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  const handleChangeDate = (
    value: Dayjs | null,
    context: PickerChangeHandlerContext<DateValidationError>
  ) => {
    if (!value) {
      return;
    }
    let month: number | undefined = value?.month();
    if (month !== undefined) {
      month = month + 1;
      const monthString = "0" + month;
      setCreatedAt(value?.year() + "-" + monthString + "-" + value?.date());
    }
  };

  const changeName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const changeDescription = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescription(event.target.value);
  };

  const changeGenre = (event: React.ChangeEvent<HTMLInputElement>) => {
    setGenre(event.target.value);
  };

  const changePicture = (picture: any) => {
    setPicture(picture);
  };

  const changeFile = (file: any) => {
    setFile(file);
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await MusicService.createSong({
      name: name,
      description: description,
      created_at: created_at,
      picture: "test",
      file: "test",
      duration: 200,
      genre: genre,
      artist_id: "Tzanca Uraganul",
    }).then((res) => {
      setSongId(res.id);
      console.log(res);
    });
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleSubmitUpload = async (
    event: React.FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();
    await MusicService.uploadToSong(songId, file, picture).then((res) => {
      console.log(res);
    });
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  return (
    <Box sx={{ ml: "240px", mr: "240px" }}>
      <Typography
        variant="h2"
        sx={{ width: "100%", margin: "0 auto", display: "flex" }}
      >
        Upload a Song
      </Typography>
      <Stepper activeStep={activeStep}>
        {steps.map((label, index) => {
          const stepProps: { completed?: boolean } = {};
          const labelProps: {
            optional?: React.ReactNode;
          } = {};
          if (isStepOptional(index)) {
            labelProps.optional = (
              <Typography variant="caption">Optional</Typography>
            );
          }
          if (isStepSkipped(index)) {
            stepProps.completed = false;
          }
          return (
            <Step key={label} {...stepProps}>
              <StepLabel>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep === steps.length && (
        <React.Fragment>
          <Typography sx={{ mt: 2, mb: 1 }}>
            All steps completed - you&apos;re finished
          </Typography>
          <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
            <Box sx={{ flex: "1 1 auto" }} />
            <Button onClick={handleReset}>Reset</Button>
          </Box>
        </React.Fragment>
      )}
      {activeStep === 0 && (
        <React.Fragment>
          <form onSubmit={handleSubmit}>
            <TextField
              label="Song Title"
              name="name"
              id="name"
              value={name}
              onChange={changeName}
              focused
            />
            <TextField
              label="Description"
              name="description"
              value={description}
              onChange={changeDescription}
              id="description"
              focused
            />
            <TextField
              label="Genre"
              name="genre"
              id="genre"
              value={genre}
              onChange={changeGenre}
              focused
            />
            <DatePicker
              className="created_at"
              onChange={(
                value: Dayjs | null,
                context: PickerChangeHandlerContext<DateValidationError>
              ) => handleChangeDate(value, context)}
            />
            <Button
              type="submit"
              variant="contained"
              onClick={() => handleSubmit}
              sx={{
                background: "#484D79",
                textTransform: "none",
                fontSize: "15px",
                mt: "10%",
                mb: 2,
                ml: "15%",
              }}
            >
              Create Song
            </Button>
          </form>
        </React.Fragment>
      )}
      {activeStep === 1 && (
        <React.Fragment>
          <form onSubmit={handleSubmitUpload}>
            <MuiFileInput
              id="picture"
              label="Picture"
              value={picture}
              onChange={changePicture}
            />
            <MuiFileInput
              id="file"
              label="File"
              value={file}
              onChange={changeFile}
            />
            <Button
              type="submit"
              variant="contained"
              onClick={() => handleSubmitUpload}
              sx={{
                background: "#484D79",
                textTransform: "none",
                fontSize: "15px",
                mt: "10%",
                mb: 2,
                ml: "15%",
              }}
            >
              Upload files
            </Button>
          </form>
        </React.Fragment>
      )}
    </Box>
  );
};
