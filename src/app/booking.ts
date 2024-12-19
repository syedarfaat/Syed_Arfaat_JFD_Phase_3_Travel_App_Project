


export interface Booking {
    id: number;
    user: { id: number, username: string, password: string, role: string };
    cab: { id: number, source: string, destination: string, type: string, kms: number, cost: number };
    dateTime: string;
    status: string;
    pickupLocation: string;
    dropoffLocation: string;
    fare: number;
  }