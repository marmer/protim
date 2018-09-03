import * as React from 'react';

export interface BookingEntryViewState {
}

export interface BookingEntryViewProps {
}

export default class BookingEntryView extends React.Component<BookingEntryViewProps, BookingEntryViewState> {
    constructor(props: BookingEntryViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        return (
            <tr>
                <td>
                    <input type="time" placeholder="e.g. 09:25"/>
                </td>
                <td>
                    <input type="time" placeholder="e.g. 01:15"/>
                </td>
                <td>
                    <textarea rows={1} placeholder="What has been done"/>
                </td>
                <td>
                    <textarea rows={1} placeholder="personal notes"/>
                </td>
                <td>
                    <textarea rows={1} placeholder="TICKET-123"/>
                </td>
                <td>
                    <div className="btn-group">
                        <button type="button" className="btn btn-primary" title="synced">
                            <i className="fas fa-lock"/>
                        </button>

                        <button type="button" className="btn btn-primary" title="synced">
                            <i className="fas fa-lock-open"/>
                        </button>
                        <button type="button" className="btn btn-primary" title="sync">
                            <i className="fas fa-sync-alt"/>
                        </button>
                        <button type="button" className="btn btn-danger" title="remove">
                            <i className="fas fa-trash-alt"/>
                        </button>
                    </div>
                </td>
            </tr>
        );
    }
}