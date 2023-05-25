import { Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import "./inbox.css";

interface Message {
  id: number;
  sender: string;
  subject: string;
  body: string;
  read: string;
  tag: string;
}

const messages: Message[] = [
  {
    id: 1,
    sender: "John Doe",
    subject: "Hello There This is A Test Message",
    body: "Hi there!",
    read: "false",
    tag: "inbox",
  },
  {
    id: 2,
    sender: "Jane Smith",
    subject: "Meeting tomorrow",
    body: "Just a reminder that we have a meeting at 2 PM tomorrow.",
    read: "false",
    tag: "inbox",
  },
  {
    id: 3,
    sender: "Bob Johnson",
    subject: "Update on project",
    body: "Here is the latest update on the project we are working on.",
    read: "false",
    tag: "inbox",
  },
  {
    id: 4,
    sender: "John Doe",
    subject: "Hello There This is A Test Message",
    body: "Hi there!",
    read: "false",
    tag: "inbox",
  },
  {
    id: 5,
    sender: "Jane Smith",
    subject: "Meeting tomorrow",
    body: "Just a reminder that we have a meeting at 2 PM tomorrow.",
    read: "false",
    tag: "inbox",
  },
  {
    id: 6,
    sender: "Bob Johnson",
    subject: "Update on project",
    body: "Here is the latest update on the project we are working on.",
    read: "false",
    tag: "inbox",
  },
  {
    id: 7,
    sender: "John Doe",
    subject: "Hello There This is A Test Message",
    body: "Hi there!",
    read: "false",
    tag: "inbox",
  },
  {
    id: 8,
    sender: "Jane Smith",
    subject: "Meeting tomorrow",
    body: "Just a reminder that we have a meeting at 2 PM tomorrow.",
    read: "false",
    tag: "inbox",
  },
  {
    id: 9,
    sender: "Bob Johnson",
    subject: "Update on project",
    body: "Here is the latest update on the project we are working on.",
    read: "false",
    tag: "inbox",
  },
];

export const InboxPage: React.FC = () => {
  const [selectedEmailId, setSelectedEmailId] = useState<number | string>(1);
  const [currentSection] = useState("inbox");
  const [emails] = useState(messages);
  const [currentEmail, setCurrentEmail] = useState<Message | undefined>(
    undefined
  );

  useEffect(() => {
    setCurrentEmail(emails.find((x) => x.id === selectedEmailId));
  }, [selectedEmailId, emails]);

  const openEmail = (id: number) => {
    const index = emails.findIndex((x) => x.id === id);
    emails[index].read = "true";
    setSelectedEmailId(id);
  };

  const deleteMessage = (id: number) => {
    const index = emails.findIndex((x) => x.id === id);
    emails[index].tag = "deleted";

    for (const email of emails) {
      if (email.tag === currentSection) {
        setSelectedEmailId(email.id);
        break;
      }
    }
  };

  return (
    <Box
      sx={{
        width: "100%",
        display: "flex",
        alignItems: "center",
        height: "100vh",
        mb: "20px",
        justifyContent: "center",
        margin: "0 auto",
      }}
    >
      <div className="inbox-container">
        <EmailList
          emails={emails.filter((x) => x.tag === currentSection)}
          onEmailSelected={(id: number) => {
            openEmail(id);
          }}
          selectedEmailId={selectedEmailId}
        />
        <EmailDetails
          email={currentEmail}
          onDelete={(id: number) => {
            deleteMessage(id);
          }}
        />
      </div>
    </Box>
  );
};

interface EmailListProps {
  emails: Message[];
  onEmailSelected: (id: number) => void;
  selectedEmailId: number | string;
}

interface EmailListItemProps {
  email: Message;
  onEmailSelected: (id: number) => void;
  selectedEmailId: number | string;
}

const EmailListItem: React.FC<EmailListItemProps> = ({
  email,
  onEmailSelected,
  selectedEmailId,
}) => {
  let classes = "email-item";
  if (selectedEmailId) {
    classes += " selected";
  }

  return (
    <div
      onClick={() => {
        onEmailSelected(email.id);
      }}
      className={classes}
    >
      <div className="email-item__unread-dot" data-read={email.read}></div>
      <div className="email-item__subject truncate">{email.subject}</div>
      <div className="email-item__details">
        <span className="email-item__from truncate">{email.sender}</span>
      </div>
    </div>
  );
};

interface EmailDetailsProps {
  email: Message | undefined;
  onDelete: (id: number) => void;
}

const EmailDetails: React.FC<EmailDetailsProps> = ({ email, onDelete }) => {
  if (!email) {
    return <div className="email-content empty"></div>;
  }

  const getDeleteButton = () => {
    if (email.tag !== "deleted") {
      return (
        <span
          onClick={() => {
            onDelete(email.id);
          }}
          className="delete-btn fa fa-trash-o"
        ></span>
      );
    }
    return undefined;
  };

  return (
    <div className="email-content">
      <div className="email-content__header">
        <h3 className="email-content__subject">{email.subject}</h3>
        {getDeleteButton()}
        <div className="email-content__from">{email.sender}</div>
      </div>
      <div className="email-content__message">{email.body}</div>
    </div>
  );
};

interface EmailListProps {
  emails: Message[];
  onEmailSelected: (id: number) => void;
}
const EmailList: React.FC<EmailListProps> = ({ emails, onEmailSelected }) => {
  if (emails.length === 0) {
    return (
      <div className="email-list empty">Nothing to see here, great job!</div>
    );
  }

  return (
    <div className="email-list">
      {emails.map((email) => {
        return (
          <EmailListItem
            onEmailSelected={(id: number) => {
              onEmailSelected(id);
            }}
            email={email}
            selectedEmailId={email.id}
          />
        );
      })}
    </div>
  );
};
