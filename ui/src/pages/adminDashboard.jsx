import React, { useState } from 'react';

import { useApi } from '../contexts/ApiProvider';

function AdminDashboard() {
  const api = useApi();
  const [companies, setCompanies] = useState([]);

  const viewAllCompanyListing = () => {
    api.getCompaniesListings()
      .then((data) => {
        const groupedCompanies = data.companies.reduce((acc, company) => {
          if (!acc[company.companyName]) {
            acc[company.companyName] = {
              companyName: company.companyName,
              companyCategory: company.companyCategory,
              listings: [],
            };
          }
          acc[company.companyName].listings.push({
            listingType: company.listingType,
            listingQuantity: company.listingQuantity,
            listingCreateTime: company.listingCreateTime,
            price: company.price,
          });
          return acc;
        }, {});
        setCompanies(Object.values(groupedCompanies));
      })
      .catch((error) => console.error('An error occurred:', error));
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <p>Welcome to the admin dashboard. Manage your system here.</p>
      <button type="button" onClick={viewAllCompanyListing}>View All Company Listings</button>
      <div>
        {companies.map((company) => (
          <div key={company.companyName}>
            <h2>{company.companyName}</h2>
            <p>
              Category:
              {' '}
              {company.companyCategory}
            </p>
            <p>
              Company listings:
            </p>
            {company.listings.map((listing) => (
              <p key={`${listing.listingType}-${listing.listingCreateTime}`}>
                Class:
                {' '}
                {listing.listingType}
                {' '}
                Quantity:
                {' '}
                {listing.listingQuantity}
                {' '}
                Price:
                {' '}
                {listing.price}
                {' '}
                Created at:
                {' '}
                {listing.listingCreateTime}
              </p>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminDashboard;
